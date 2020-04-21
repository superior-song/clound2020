package com.atguigu.springcloud.config.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * rest template客户端修改
 *
 * @author jie liu
 * @date 2017/9/28.
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    @Value("${rest.maxConnTotal}")
    private String maxConnTotal;

    @Value("${rest.maxConnPerRoute}")
    private String maxConnPerRoute;

    @Value("${rest.requestTimeout}")
    private String requestTimeout;

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(wfmHttpClient());
    }

    @Bean
    public RestTemplate restTemplate() {
        if(log.isInfoEnabled()) {
            log.info("创建rest template客户端");
        }
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(messageConverters).build();
        restTemplate.setRequestFactory(httpRequestFactory());
        restTemplate.getMessageConverters().add(new AllEncompassingFormHttpMessageConverter());
        return restTemplate;
    }

    @Bean
    public HttpClient wfmHttpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(Integer.valueOf(maxConnTotal));
        connectionManager.setDefaultMaxPerRoute(Integer.valueOf(maxConnPerRoute));

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(Integer.valueOf(requestTimeout))
                .setConnectTimeout(Integer.valueOf(requestTimeout))
                .setConnectionRequestTimeout(Integer.valueOf(requestTimeout))
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
