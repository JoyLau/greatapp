/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import cn.lfdevelopment.www.common.util.IpAddrUtil;
import cn.lfdevelopment.www.common.util.WebUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
public class KickOut extends AccessControlFilter {

    private final String kickoutUrl = "/repeatLogin?kickout=true"; //踢出后转向的地址

    @Autowired
    private DefaultWebSessionManager sessionManager;

    @Autowired
    private DefaultWebSecurityManager defaultWebSecurityManager;

    /**
     * Setter method for property <tt>cache</tt>.
     *
     * @param cache value to be assigned to property cache
     */
    public void setCache(Cache<String, Deque<Serializable>> cache) {
        this.cache = defaultWebSecurityManager.getCacheManager().getCache("shiro-kickout-session");
    }

    private Cache<String, Deque<Serializable>> cache;

    @Value("${spring.shiro.session}")
    private boolean shiroSession;

    //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    @Value("${spring.shiro.session.kicOutAfter}")
    private boolean shiroSessionKicOutAfter;

    //同一个帐号最大会话数 默认1
    @Value("${spring.shiro.session.maxSession}")
    private int shiroSessionMaxSession;

    @Value("${spring.shiro.ip}")
    private boolean shiroIp;

    @Value("${spring.shiro.ip.kicOutAfter}")
    private boolean shiroIpKicOutAfter;

    @Value("${spring.shiro.ip.maxSession}")
    private int shiroIpMaxSession;



    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        //Processing not login requests;
        if(!subject.isAuthenticated()) {
            return true;
        }
        //Custom process the request , make its not forward for Shiro;
        if (shiroSession) {
            return useSession(subject, request, response);
        }
        return useIp(subject, request, response);
    }


    /**
     * @LiuFa
     * 使用配置的IP过滤法来限制登录
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private boolean useIp(Subject subject,ServletRequest request, ServletResponse response) throws IOException {
        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();
        String ip = IpAddrUtil.getIpAddr((HttpServletRequest) request);

        Deque<Serializable> deque = cache.get(username);
        if(deque == null) {
            deque = new LinkedList<>();
            cache.put(username, deque);
        }

        //放入队列
        if(!deque.contains(sessionId+","+ip) && session.getAttribute("kickout") == null) {
            deque.push(sessionId+","+ip);
        }

        //开始踢人
        while (deque.size() > shiroIpMaxSession && !deque.getFirst().toString().split(",")[1].equals(deque.getLast()
                .toString().split(",")[1])) {
            Serializable kickoutSessionId;
            if (shiroIpKicOutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst().toString().split(",")[0];
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast().toString().split(",")[0];
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {
                //ignore exception
            }
        }

        //被踢重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            try {
                subject.logout();
            } catch (Exception e) {
                //ignore
            }
            if (WebUtil.isAjax(request)) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                //Setting the session state in response headers;
                httpServletResponse.setHeader("sessionStatus", "timeout");
                httpServletResponse.setHeader("URI", httpServletRequest.getContextPath() + kickoutUrl);
                return false;
            }

            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }


    /**
     * @LiuFa
     * 使用配置的Session过滤法来限制登录
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private boolean useSession(Subject subject,ServletRequest request, ServletResponse response) throws IOException {
        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        Deque<Serializable> deque = cache.get(username);
        if(deque == null) {
            deque = new LinkedList<>();
            cache.put(username, deque);
        }

        //放入队列
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }

        //开始踢人
        while(deque.size() > shiroSessionMaxSession) {
            Serializable kickoutSessionId;
            if(shiroSessionKicOutAfter) {
                kickoutSessionId = deque.removeFirst();
            } else {
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {
                //ignore exception
            }
        }

        if (session.getAttribute("kickout") != null) {
            try {
                subject.logout();
            } catch (Exception e) {
                //ignore
            }
            if (WebUtil.isAjax(request)) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                //Setting the session state in response headers;
                httpServletResponse.setHeader("sessionStatus", "timeout");
                httpServletResponse.setHeader("URI", httpServletRequest.getContextPath() + kickoutUrl);
                return false;
            }

            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }
}