package com.decagon.eventhubbe.config;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.decagon.eventhubbe.utils.PaymentUtils.getSecretKey;


@RequiredArgsConstructor
@Data
public class AccountPaymentOveridden {
    private static HttpHeaders headers;
    private static  RestTemplate restTemplate;

    public static  <T> ResponseEntity<T> performPostRequest(String url, MultiValueMap<String, String> body, Class<T> responseType) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(getSecretKey());
        headers.setCacheControl("no-cache");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(url, httpEntity, responseType);
    }

}
