package cn.lfdevelopment.www.common.util;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JoyLau on 2017/6/9.
 * cn.lfdevelopment.www.common.util
 * 2587038142@qq.com
 */
public class CountDownLatchTest {
    // 模拟并发登录网址，查看服务器峰值
    public static void main(String[] args) throws InterruptedException {

        // 锁住所有线程，等待并发执行
        final CountDownLatch begin = new CountDownLatch(1);


        final ExecutorService exec = Executors.newFixedThreadPool(110);

        for (int index = 0; index < 1; index++) {
            final int NO = index + 1;

            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 等待，所有一起执行
                        begin.await();
                        //开始模拟登录等待。。。
                        try {
                            test();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        System.out.println("No." + NO + " execute");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.submit(run);
        }

        System.out.println("开始执行");
        // begin减一，开始并发执行
        begin.countDown();

        //关闭执行
        exec.shutdown();
    }



    public static void test() throws IOException, URISyntaxException {
        URI uri = new URI("https://api.baidu.com/json/tongji/v1/ReportService/getData?site_id=691c26474b1733df80dca4dfa7ba5536&method=overview/getTimeTrendRpt&start_date=20170501&end_date=20170701&metrics=pv_count,visitor_count,new_visitor_count");

        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        ClientHttpRequest clientHttpRequest = simpleClientHttpRequestFactory.createRequest(uri, HttpMethod.POST);
        ClientHttpResponse response = clientHttpRequest.execute();



        System.out.println(response.getStatusCode().toString());
        InputStream is = response.getBody();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str;
        while((str = br.readLine())!=null){
            System.out.println(str);
        }
    }



}
