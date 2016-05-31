package com.cheyipai.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.cheyipai.utils.string.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class HttpUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);
    public static final String GET = "GET";
    public static final String POST = "POST";
    private static final int connectionTimeout = 2000;
    private static final int soTimeout = 2000;

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doGet(String url, List<NameValuePair> params) {
        long startTime = System.currentTimeMillis();
        int statusCode = 200;
        HttpGet httpGet = null;
        String respContent = null;
        HttpEntity entity = null;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpParams httpparams = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpparams, connectionTimeout);
        HttpConnectionParams.setSoTimeout(httpparams, soTimeout);
        try {
            httpGet = new HttpGet(url);
            // 设置参数we
            if (params != null && params.size() > 0) {
                httpGet.setURI(new URI(httpGet.getURI().toString() + "?"
                        + EntityUtils.toString(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8))));
            }
            // 发送请求
            HttpResponse httpResponse = httpclient.execute(httpGet);
            // 获取返回数据
            entity = httpResponse.getEntity();
            respContent = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            entity.getContent();
            // 获取状态码
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine != null) {
                statusCode = statusLine.getStatusCode();
            }
        } catch (IOException e) {
            httpLogInfo(GET, url, params, 0L, 500, null, e.getMessage());
            e.printStackTrace();
        } catch (URISyntaxException e) {
            httpLogInfo(GET, url, params, 0L, 500, null, e.getMessage());
            e.printStackTrace();
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }
        httpLogInfo(GET, url, params, System.currentTimeMillis() - startTime, statusCode, respContent, null);
        return respContent;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPost(String url, List<NameValuePair> params) {
        long startTime = System.currentTimeMillis();
        int statusCode = 200;
        HttpPost httpPost = null;
        String respContent = null;
        HttpEntity entity = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpParams httpparams = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpparams, connectionTimeout);
        HttpConnectionParams.setSoTimeout(httpparams, soTimeout);
        try {
            httpPost = new HttpPost(url);
            // 设置参数
            if (params != null && params.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 获取返回数据
            entity = httpResponse.getEntity();
            respContent = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            entity.getContent();
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine != null) {
                statusCode = statusLine.getStatusCode();
            }
        } catch (IOException e) {
            httpLogInfo(POST, url, params, 0L, 500, null, e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            httpLogInfo(POST, url, params, 0L, 500, null, e.getMessage());
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        httpLogInfo(POST, url, params, System.currentTimeMillis() - startTime, statusCode, respContent, null);
        return respContent;
    }

    private static void httpLogInfo(String method, String url, List<NameValuePair> params, long time, int code, String result, String error) {
        JSONObject object = new JSONObject();
        object.put("method", method);
        object.put("date", (new Date()).getTime());
        object.put("url", url);
        object.put("params", params);
        object.put("httpCode", code);
        if (StringUtils.isNotBlank(error)) {
            object.put("error", error);
        } else {
            object.put("costTime(ms)", time);
            object.put("result", result);
        }
        LOG.info(object.toJSONString());
    }
}
