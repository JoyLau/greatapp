/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.common.util;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.common.util
 * DevelopmentApp
 */
public class WebUtil {
    /**
     * Verify the asynchronous request
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request) {
        return ((HttpServletRequest) request).getHeader("X-requested-with") != null
                && "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * is Post request
     * @param request
     * @return
     */
    public static boolean isPost(ServletRequest request) {
        return ((HttpServletRequest) request).getMethod().equalsIgnoreCase("POST");
    }

    /**
     * return asynchronous messages;
     * Usually, we can also use HashMap
     * @param response
     * @param str
     */
    public static void PrintOut(ServletResponse response, String str) {
        PrintWriter pw = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            pw = response.getWriter();
            pw.println("<font color = 'red'>" + str + "</font>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != pw) {
                pw.flush();
                pw.close();
            }
        }
    }

    /**
     *
     * @param url
     */
    public static boolean pushARequest(String url){
        try {
            URI uri = new URI(url);
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            ClientHttpRequest clientHttpRequest = simpleClientHttpRequestFactory.createRequest(uri, HttpMethod.POST);
            clientHttpRequest.execute();
            return true;
        } catch (URISyntaxException | IOException e) {
            return false;
        }
    }
}
