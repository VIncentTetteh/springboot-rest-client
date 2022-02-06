package com.vincent.restclient.restclientService;

import com.vincent.restclient.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class RestClientService {
    private final String USER_URL = "https://jsonplaceholder.typicode.com/posts";
    private Logger logger = LoggerFactory.getLogger(RestClientService.class);
    private final RestTemplate restTemplate;

    public RestClientService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public List<User> getClient(){
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(
                        USER_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<User>>() {}
                );
        List<User> users = responseEntity.getBody();
        return users.stream().collect(Collectors.toList());
    }


    public List<String> getTitle(){
        List<String> title = getClient().stream().map(post -> post.getTitle()).collect(Collectors.toList());

        return title;
    }

    public List<User> findUserById(Long userId) {
        return getClient().stream().filter(post -> post.getUserId() == userId).collect(Collectors.toList());
    }
}
