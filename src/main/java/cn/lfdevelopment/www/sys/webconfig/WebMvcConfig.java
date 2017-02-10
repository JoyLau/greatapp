/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.webconfig;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by LiuFa on 2016/8/9.
 * cn.lfdevelopment.www.sys
 * DevelopmentApp
 * WebMvc配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * @author LiuFa
     * 静态资源文件路径配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//      resourceHandlerRegistry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
        super.addResourceHandlers(resourceHandlerRegistry);
    }

    /**
     * @author LiuFa
     * 集中管理无任何业务处理的页面转向
     */
    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry){
        /*开发考题系统的配置*/
//        viewControllerRegistry.addViewController("/").setViewName("redirect:/main");
//        viewControllerRegistry.addViewController("/index").setViewName("redirect:/main");

        /*开发个人页面的配置*/
        viewControllerRegistry.addViewController("/").setViewName("redirect:/index");


        viewControllerRegistry.addViewController("/404").setViewName("404");
        viewControllerRegistry.addViewController("/500").setViewName("500");

    }

    /**
     * @author LiuFa
     * 对404,500页面的管理
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new ContainerCustomizer();
    }

    private static class ContainerCustomizer implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND , "/404"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500"));
        }
    }
}
