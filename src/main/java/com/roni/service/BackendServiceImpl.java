package com.roni.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.client.RestTemplate;

@Component
public class BackendServiceImpl implements BackendService {
    @Autowired
    private RestTemplate restTemplate;


}