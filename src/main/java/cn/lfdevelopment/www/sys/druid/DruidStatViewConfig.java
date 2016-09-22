/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LiuFa on 2016/8/8.
 * cn.lfdevelopment.www.sys.druid
 * DevelopmentApp
 * 这样的方式不需要添加注解：@ServletComponentScan
 */
@Configuration
public class DruidStatViewConfig {

    @Value("${spring.druid.loginUsername}")
    private String loginUsername;

    @Value("${spring.druid.loginPassword}")
    private String loginPassword;

    /**
     * 注册一个StatViewServlet
     * 使用Druid的内置监控页面
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        //添加初始化参数：initParams
        //白名单
        //ip配置规则
        //配置的格式
        //<IP> 或者 <IP>/<SUB_NET_MASK_size> 多个ip地址用逗号隔开
        //其中
        //128.242.127.1/24
        //24表示，前面24位是子网掩码，比对的时候，前面24位相同就匹配。
        //由于匹配规则不支持IPV6，配置了allow或者deny之后，会导致IPV6无法访问。
        servletRegistrationBean.addInitParameter("allow", "");

        //deny优先于allow，如果在deny列表中，就算在allow列表中，也会被拒绝。
        //如果allow没有配置或者为空，则允许所有访问
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "");

        //登录查看信息的账号密码.
        try {
            servletRegistrationBean.addInitParameter("loginUsername", ConfigTools.decrypt(loginUsername));
            servletRegistrationBean.addInitParameter("loginPassword", ConfigTools.decrypt(loginPassword));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "true");

        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * 内置监控中的Web关联监控的配置
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //排除一些不必要的url
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        //缺省sessionStatMaxCount是1000个,这里设置了3000个
        filterRegistrationBean.addInitParameter("sessionStatMaxCount", "3000");
        //可以配置principalCookieName，使得druid知道指定的sessionName是谁
//        filterRegistrationBean.addInitParameter("principalSessionName", "sessionId");
        //druid 0.2.7版本开始支持profile，配置profileEnable能够监控单个url调用的sql列表。
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    /**
     * 注册一个:druidStatInterceptor
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor(){
        return new DruidStatInterceptor();
    }

    /**
     * 注册一个：beanNameAutoProxyCreator
     * 内置监控中的spring关联监控的配置
     * 该方法使用的是按照BeanId来拦截配置，还有2种方法，分别是
     * 按类型拦截配置
     * 方法名正则匹配拦截配置
     */
    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        beanNameAutoProxyCreator.setBeanNames("*Controller");
        beanNameAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
        return beanNameAutoProxyCreator;
    }
}
