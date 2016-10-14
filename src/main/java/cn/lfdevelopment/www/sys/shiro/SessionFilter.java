/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import cn.lfdevelopment.www.common.util.WebUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuFa on 2016/10/14.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
public class SessionFilter extends AccessControlFilter {
    public SessionFilter() {
    }

    /**
     * 在此处理未登录的请求，已经登录的请求交由后续过滤器链处理
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    /**
     *  处理未登录的ajax的请求，返回服务器状态码为401<无权限访问></>
     *  对于同步请求交给后续过滤器链，转向/login
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (WebUtil.isAjax(WebUtils.toHttp(request))) {
            HttpServletResponse res = WebUtils.toHttp(response);
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
