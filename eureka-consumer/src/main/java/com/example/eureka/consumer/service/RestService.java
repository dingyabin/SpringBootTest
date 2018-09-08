package com.example.eureka.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 *
 * @author dingyabin
 * @date 2018/4/19
 * Time: 14:54
 * function:
 */
@Slf4j
@Service
public class RestService {

    @Resource
    private RestTemplate restTemplate;

    private final String PROVIDERNAME = "PROVIDER";


    public String remoteCall(){
        String url = "http://" + PROVIDERNAME + "/service/v1";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<String>("{}", httpHeaders);
        return restTemplate.postForObject(url, httpEntity, String.class);
    }


}
