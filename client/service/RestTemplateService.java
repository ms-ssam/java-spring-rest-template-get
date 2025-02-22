package com.example.client.service;

import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
// RestTemplate 통해 get으로 통신하는 방법, UriComponentBuilder로 주소 만드는 방법 학습
@Service
public class RestTemplateService {

    // http://localhost/api/server/hello 주소 호출해서 response 받기
    public UserResponse hello() {
        // 주소 만들기 ("http://localhost:9090/api/server/hello")
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "aaaa")
                .queryParam("age", 99)
                .encode()  // 선택사항
                .build()
                .toUri();
        System.out.println(uri);

        RestTemplate restTemplate = new RestTemplate();
        // uri로 요청, 결과를 String으로 받음
        // 메서드명의 get은 가져온다는 의미가 아니라 http method get을 의미 (get에 대한 메서드 처리)
        // getForObject가 실행되는 순간에 클라이언트가 http로 서버에 붙음
        //String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }
}
