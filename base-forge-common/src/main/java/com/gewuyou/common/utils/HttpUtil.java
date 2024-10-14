package com.gewuyou.common.utils;

import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.common.exception.GlobalException;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Http工具
 *
 * @author gewuyou
 * @since 2024-10-04 23:08:23
 */
public class HttpUtil {
    private static final HttpClient client = HttpClient.newHttpClient();

    private HttpUtil() {
    }

    /**
     * 发送GET请求
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求参数
     * @return HttpResponse<String>
     * @throws IOException IO异常
     * @throws InterruptedException 中断异常
     */
    public static HttpResponse<String> sendGet(String url, Map<String, String> headers, Map<String, Object> params) throws IOException, InterruptedException {
        HttpRequest request;
        if (!CollectionUtils.isEmpty(params)) {
            url = buildUrlWithParams(url, params);
        }
        if (!CollectionUtils.isEmpty(headers)) {
            String[] headerArr = headers.entrySet()
                    .stream()
                    // 将键值对转换为键、值的流
                    .flatMap(entry -> Stream.of(entry.getKey(), entry.getValue()))
                    // 收集为字符串数组
                    .toArray(String[]::new);
            request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .headers(headerArr)
                    .GET()
                    .build();
        } else {
            request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        }
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * 发送POST请求
     * @param url 请求地址
     * @param headers 请求头
     * @param requestBody 请求体
     * @return HttpResponse<String>
     * @throws IOException IO异常
     * @throws InterruptedException 中断异常
     */
    public static HttpResponse<String> sendPost(String url, Map<String, String> headers, HttpRequest.BodyPublisher requestBody) throws IOException, InterruptedException {
        HttpRequest request;
        if (!CollectionUtils.isEmpty(headers)) {
            String[] headerArr = headers.entrySet()
                    .stream()
                    // 将键值对转换为键、值的流
                    .flatMap(entry -> Stream.of(entry.getKey(), entry.getValue()))
                    // 收集为字符串数组
                    .toArray(String[]::new);
            request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .headers(headerArr)
                    .POST(requestBody)
                    .build();
        } else {
            request = HttpRequest.newBuilder().uri(URI.create(url)).POST(requestBody).build();
        }
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static String buildUrlWithParams(String baseUrl, Map<String, Object> params) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                try {
                    String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);
                    String value = URLEncoder.encode(String.valueOf(entry.getValue()), StandardCharsets.UTF_8);
                    urlBuilder.append(key).append("=").append(value).append("&");
                } catch (Exception e) {
                    throw new GlobalException(ResponseInformation.URL_ENCODE_ERROR);
                }
            }
            // 去掉最后一个多余的 &
            urlBuilder.setLength(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }

}
