/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
//@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ShiroConfig {

    /*@Autowired
    private KickOut kickOut;
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
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
    @Bean(name = "ehcacheManager")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }

    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheCacheManager;

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

    @Bean(name = "sessionIdCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        simpleCookie.setName("jsid");
        simpleCookie.setPath("/");
        return simpleCookie;
    }

    @Bean(name = "sessionDAO")
    public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(){
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        enterpriseCacheSessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return enterpriseCacheSessionDAO;
    }
//    @Bean(name = "sessionValidationScheduler")
//    public QuartzSessionValidationScheduler quartzSessionValidationScheduler(){
//        QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler();
//        quartzSessionValidationScheduler.setSessionManager(defaultWebSessionManager());
//        quartzSessionValidationScheduler.setSessionValidationInterval(600000);
//        return quartzSessionValidationScheduler;
//
//    }
    @Bean(name = "sessionManage")
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = null;
        try {
            defaultWebSessionManager = new DefaultWebSessionManager();
            defaultWebSessionManager.setGlobalSessionTimeout(1200000);
            defaultWebSessionManager.setDeleteInvalidSessions(true);
            defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
            QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler();
            quartzSessionValidationScheduler.setSessionValidationInterval(600000);
            defaultWebSessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler);
            defaultWebSessionManager.setSessionDAO(enterpriseCacheSessionDAO());
            defaultWebSessionManager.setSessionIdCookieEnabled(true);
            defaultWebSessionManager.setSessionIdCookie(simpleCookie());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return defaultWebSessionManager;
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(new AuthorizingRealm());
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager());
//        defaultWebSecurityManager.setCacheManager(new SpringCacheManagerWrapper(ehCacheCacheManager()));
        return defaultWebSecurityManager;
    }



    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiro = new ShiroFilterFactoryBean();
        shiro.setSecurityManager(defaultWebSecurityManager());
        shiro.setLoginUrl("/login");
        shiro.setSuccessUrl("/main");
//        验证不具备权限后的转向页面
        shiro.setUnauthorizedUrl("/main");
        Map<String,Filter> map = new LinkedHashMap<>();
        map.put("authc",shiroFormAuthenticationFilter());
        map.put("rolesOr",rolesAuthorizationFilter());
        map.put("kickout",kickOut);
        shiro.setFilters(map);


        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/resources*//**","anon");
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/anon*//**","anon");
        filterChainDefinitionMap.put("/cometd*//**","anon");
        filterChainDefinitionMap.put("/repeatLogin","anon");
        filterChainDefinitionMap.put("/ueditor*//**","anon");
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/login","authc");
        filterChainDefinitionMap.put("/main","authc");
        filterChainDefinitionMap.put("/case*//**","kickout,user,rolesOr[SUPER,TEACHER]");
        filterChainDefinitionMap.put("/sys/admin*//**","kickout,user,roles[ADMIN]");
        filterChainDefinitionMap.put("/superAdmin*//**","kickout,user,roles[SUPER]");
        filterChainDefinitionMap.put("/teacher*//**","kickout,user,roles[TEACHER]");
        filterChainDefinitionMap.put("/student*//**","kickout,user,roles[STUDENT]");
        filterChainDefinitionMap.put("*//**","kickout,user");
        shiro.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiro;
    }*/
}
