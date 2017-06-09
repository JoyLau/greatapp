package cn.lfdevelopment.www.common.util.httpclient;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by JoyLau on 2017/6/9.
 * cn.lfdevelopment.www.common.util.httpclient
 * 2587038142@qq.com
 */
public class TestHttpClient {
    // 创建CookieStore实例
    static CookieStore cookieStore = null;
    static HttpClientContext context = null;
    static String loginUrl = "http://dphd.gtafe.com/dphd_web/login";
    static String mainUrl = "http://dphd.gtafe.com/dphd_web/main";

    public static void testLogin() throws Exception {
        // 直接创建client
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(loginUrl);
        HashMap<String,String> parameterMap = new HashMap<>();
        parameterMap.put("username", "admin");
        parameterMap.put("password", "111111");
        parameterMap.put("identity", "admin");
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(parameterMap), "UTF-8");
        httpPost.setEntity(postEntity);
        System.out.println("request line:" + httpPost.getRequestLine());
        try {
            // 执行post请求
            HttpResponse httpResponse = client.execute(httpPost);
            printResponse(httpResponse);

            // 执行get请求
            System.out.println("----the same client");
            HttpGet httpGet = new HttpGet(mainUrl);
            System.out.println("request line:" + httpGet.getRequestLine());
            HttpResponse httpResponse1 = client.execute(httpGet);
            printResponse(httpResponse1);


            // cookie store
//            setCookieStore(httpResponse);
            // context
//            setContext();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流并释放资源
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        try {
            testLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void testContext() throws Exception {
        System.out.println("----testContext");
        // 使用context方式
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(testUrl);
        System.out.println("request line:" + httpGet.getRequestLine());
        try {
            // 执行get请求
            HttpResponse httpResponse = client.execute(httpGet, context);
            System.out.println("context cookies:"
                    + context.getCookieStore().getCookies());
            printResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流并释放资源
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void testCookieStore() throws Exception {
        System.out.println("----testCookieStore");
        // 使用cookieStore方式
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).build();
        HttpGet httpGet = new HttpGet(testUrl);
        System.out.println("request line:" + httpGet.getRequestLine());
        try {
            // 执行get请求
            HttpResponse httpResponse = client.execute(httpGet);
            System.out.println("cookie store:" + cookieStore.getCookies());
            printResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流并释放资源
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    public static void printResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        System.out.println("status:" + httpResponse.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            System.out.println("response length:" + responseString.length());
            System.out.println("response content:" + responseString);
        }
    }

    public static void setContext() {
        System.out.println("----setContext");
        context = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder
                .<CookieSpecProvider> create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY,
                        new BrowserCompatSpecFactory()).build();
        context.setCookieSpecRegistry(registry);
        context.setCookieStore(cookieStore);
    }

    public static void setCookieStore(HttpResponse httpResponse) {
        System.out.println("----setCookieStore");
        cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie")
                .getValue();
        String JSESSIONID = setCookie.substring("joylauid=".length(),
                setCookie.indexOf(";"));
        System.out.println("JSESSIONID:" + JSESSIONID);
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("joylauid",
                JSESSIONID);
        cookie.setVersion(0);
        cookie.setDomain("http://dphd.gtafe.com/dphd_web/");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
    }

    public static List<NameValuePair> getParam(Map parameterMap) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        Iterator it = parameterMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry parmEntry = (Entry) it.next();
            param.add(new BasicNameValuePair((String) parmEntry.getKey(),
                    (String) parmEntry.getValue()));
        }
        return param;
    }
}
