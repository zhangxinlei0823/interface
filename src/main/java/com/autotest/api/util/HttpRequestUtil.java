package com.autotest.api.util;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.SystemOutLogger;

import java.io.IOException;

/**
 * @version V1.0
 * @Title: HttpRequestUtil
 * @Package com.autotest.api.util
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-12 14:39
 */
public class HttpRequestUtil {
    /**
     * 初始化
     */
    private static BasicCookieStore basicCookieStore = new BasicCookieStore();

    public static void httpReqConfig(HttpRequestBase httpRequestBase, String param){
        httpRequestBase.setHeader("User-Agent","application/x-www-form-urlencoded; charset=UTF-8");
        if(new ParseJsonToMapUtil().isJsonString(param)){
            httpRequestBase.setHeader("Content-Type","application/json;charset=UTF-8");
        }else{
            httpRequestBase.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        }
        //设置超时时间
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectionRequestTimeout(2000);
        RequestConfig requestConfig = builder.build();
        httpRequestBase.setConfig(requestConfig);
    }

    public static String sendGet(String url, String param){
        /**
         * 初始化对象
         */
        CloseableHttpResponse closeableHttpResponse = null;
        String result = null;

        //设置请求url
        String reqUrl = url+"?"+param;

        HttpGet httpGet = new HttpGet(reqUrl);

        //设置请求头
        httpReqConfig(httpGet,param);

        //设置cookie并得到closeableHttpClient对象
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setDefaultCookieStore(basicCookieStore);
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpGet);
            if(closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                result = EntityUtils.toString(httpEntity,"utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
/**
 * create by: zhangshao
 * @description: to send post request
 * create time: 2019-08-13 14:51
 * @params String url,String param
 * @return String
 */
    public static String sendPost(String url, String param){
        /**
         * 初始化对象
         */
        CloseableHttpResponse closeableHttpResponse = null;
        String result = null;

        //设置cookie并得到httpClient对象
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setDefaultCookieStore(basicCookieStore);
        CloseableHttpClient httpClient = httpClientBuilder.build();

        //得到httpPost对象
        HttpPost httpPost = new HttpPost(url);

        //设置请求头
        httpReqConfig(httpPost,param);
        try {
            //将字符串类型的数据转换为HttpEntity数据类型
            StringEntity stringEntity = new StringEntity(param);
            //设置消息实体,setEntity()方法是HttpEntityEnclosingRequest接口中的方法
            httpPost.setEntity(stringEntity);
            //httpPost继承了HttpEntityEnclosingRequestBase,可上转
            closeableHttpResponse = httpClient.execute(httpPost);
            //判断数据从服务器返回值是否正常
            if(closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args){
        //编码的问题会导致一些报错
        String url="http://testscg.iuoooo.com/api/Employee/v3/GetEmployeeInfo";
        String param="{\"ChooseUserId\": \"b0b2ec63-d098-4784-94d1-3f6ca73d31fb\",\"AppId\": \"128b85de-0f43-4aac-bed9-33541f84eedb\",\"OrgId\": \"639f10ce-a97f-4073-aad6-cab182b8aeaf\",\"LoginUserId\": \"b0b2ec63-d098-4784-94d1-3f6ca73d31fb\"}";
        String tmp = sendPost(url,param);
        System.out.println(tmp);
    }

}
