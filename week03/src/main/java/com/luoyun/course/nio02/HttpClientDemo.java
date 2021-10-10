package com.luoyun.course.nio02;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * HttpClientDemo
 *
 * @author luoyun
 * @data 2021/9/26
 */
public class HttpClientDemo {

    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static void main(String[] args) {
        CloseableHttpResponse response = null;
        try {
            HttpGet httpget = new HttpGet("http://localhost:8888/hello");
            response = httpclient.execute(httpget);
            if (response == null || response.getEntity() == null){
                System.out.println(response.toString());
            }
            System.out.println(EntityUtils.toString(response.getEntity()));
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (response != null){
                try {
                    response.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
