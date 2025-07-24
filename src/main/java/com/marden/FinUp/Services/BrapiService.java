package com.marden.FinUp.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BrapiService {

    private final RestTemplate restTemplate;



    public BrapiService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public String buscarAtivoPorTicker(String ticker){
            String url = "https://brapi.dev/api/quote/" + ticker + "?token=ckw8JRsm7trTNV4qK9ia6Z";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        }

}
