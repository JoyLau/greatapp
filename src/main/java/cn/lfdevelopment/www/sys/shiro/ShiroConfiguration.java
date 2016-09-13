/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LiuFa on 2016/9/13.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
@Configuration
public class ShiroConfiguration {
    /**
     * FilterRegistrationBean
     * @return
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.addInitParameter("targetFilterLifecycle","true");
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    /**
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/main");
        //验证不具备权限后的转向页面
        bean.setUnauthorizedUrl("/main");

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc",shiroFormAuthenticationFilter());
        filters.put("rolesOr",rolesAuthorizationFilter());
        bean.setFilters(filters);

        Map<String, String> chains = new LinkedHashMap<>();
        chains.put("/static/**", "anon");
        chains.put("/404", "anon");
        chains.put("/login", "anon");
        chains.put("/unauthor", "anon");
        chains.put("/logout", "logout");
        chains.put("/base/**", "anon");
        chains.put("/css/**", "anon");
        chains.put("/layer/**", "anon");
        chains.put("/**", "authc");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }


    /**
     * @see org.apache.shiro.mgt.SecurityManager
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        manager.setCacheManager(redisCacheManager());
        manager.setSessionManager(defaultWebSessionManager());
        return manager;
    }

    /**
     * @see DefaultWebSessionManager
     * @return
     */
    @Bean(name="sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(redisCacheManager());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationScheduler(new QuartzSessionValidationScheduler(){
            @Override
            public void setSessionValidationInterval(long sessionValidationInterval) {
                super.setSessionValidationInterval(600000);
            }
        });
        return sessionManager;
    }


    /**
     * @return
     */
    @Bean
    @DependsOn(value={"lifecycleBeanPostProcessor", "shrioRedisCacheManager"})
    public AuthorizingRealm userRealm() {
        AuthorizingRealm userRealm = new AuthorizingRealm();
        userRealm.setCacheManager(redisCacheManager());
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthorizationCachingEnabled(true);
        return userRealm;
    }


    @Bean(name="shrioRedisCacheManager")
    @DependsOn(value="redisTemplate")
    public ShrioRedisCacheManager redisCacheManager() {
        ShrioRedisCacheManager cacheManager = new ShrioRedisCacheManager(redisTemplate);
        return cacheManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "authcFilter")
    public FormAuthenticationFilter shiroFormAuthenticationFilter(){
        FormAuthenticationFilter shiroFormAuthenticationFilter = new FormAuthenticationFilter(){
            @Override
            protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
                WebUtils.getAndClearSavedRequest(request);
                WebUtils.redirectToSavedRequest(request,response,"/main");
                return false;
            }
        };
        shiroFormAuthenticationFilter.setUsernameParam("username");
        shiroFormAuthenticationFilter.setPasswordParam("password");
        shiroFormAuthenticationFilter.setRememberMeParam("rememberMe");
        shiroFormAuthenticationFilter.setLoginUrl("/login");

        return shiroFormAuthenticationFilter;
    }


    @Bean(name = "rolesOrFilter")
    public RolesAuthorizationFilter rolesAuthorizationFilter(){
        return new RolesAuthorizationFilter() {
            @Override
            public boolean isAccessAllowed(ServletRequest request,
                                           ServletResponse response, Object mappedValue) throws IOException {
                Subject subject = getSubject(request, response);
                String[] rolesArray = (String[]) mappedValue;

                if ((rolesArray == null) || (rolesArray.length == 0)) {
                    return true;
                }
                for (String aRolesArray : rolesArray) {
                    if (subject.hasRole(aRolesArray)) {
                        //用户只要拥有任何一个角色则验证通过
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
