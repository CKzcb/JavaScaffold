package com.ckzcb.ape.common.http.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName HttpClientUtil
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/6/3 20:57
 * @Version 1.0
 */
@Component
public class HttpClientUtil {
    static CloseableHttpClient httpClient;
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(1000))
                .build();

        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder
                .create()
                .setDefaultSocketConfig(socketConfig)
                .setMaxConnTotal(1000)
                .setMaxConnPerRoute(50)
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(8000))
                .setResponseTimeout(Timeout.ofMilliseconds(8000))
                .setConnectTimeout(Timeout.ofMilliseconds(8000))
                .build();

        httpClient = HttpClients
                .custom()
                .disableContentCompression()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    public String get(String url, Map<String, String> headers) throws URISyntaxException {
        if (httpClient == null) {
            return "";
        }
        List<NameValuePair> valuePairs = headers == null ? null :
                headers.entrySet().stream().map(e -> new BasicNameValuePair(e.getKey(),
                        e.getValue())).collect(Collectors.toList());
        URI uri = new URIBuilder(url)
                .addParameters(valuePairs)
                .build();
        HttpGet httpGet = new HttpGet(uri);
        try {
            var resp = httpClient.execute(httpGet);
            return EntityUtils.toString(resp.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        if (httpClient == null) {
            return "";
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(EntityBuilder.create()
                    .setText(objectMapper.writeValueAsString(params))
                    .setContentType(ContentType.APPLICATION_JSON)
                    .build());
            List<NameValuePair> valuePairs = headers == null ? null :
                    headers.entrySet().stream().map(e -> new BasicNameValuePair(e.getKey(),
                            e.getValue())).collect(Collectors.toList());
            URI uri = new URIBuilder(url)
                    .addParameters(valuePairs)
                    .build();
            httpPost.setUri(uri);
            var resp = httpClient.execute(httpPost);
            return EntityUtils.toString(resp.getEntity());
        } catch (IOException | ParseException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        try {
//            Map<String, String> map = Map.of("name", "张三", "age", "22");
//            System.out.println(httpClientUtil.post("http://127.0.0.1:9090/request/t9", null, map));
            Map<String, String> hearders = Map.of("name", "张三", "age", "22");
            System.out.println(httpClientUtil.get("http://127.0.0.1:9090/request/t6", hearders));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
