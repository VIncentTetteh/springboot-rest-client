package com.vincent.restclient.controller;

import com.vincent.restclient.domain.User;
import com.vincent.restclient.restclientService.RestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestClientController {
    private final RestClientService restClientService;

    public RestClientController(RestClientService restClientService) {
        this.restClientService = restClientService;
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return restClientService.getClient();
    }
    @GetMapping("/titles")
    public List<String> getUserTitles(){
        return restClientService.getTitle();
    }
    @GetMapping("/users/{id}")
    public List<User> findUserById(@RequestParam(value = "id") Long userId){
        return restClientService.findUserById(userId);
    }
}
