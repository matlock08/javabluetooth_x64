package com.roni.service;

import com.roni.config.ApplicationProperties;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class BackendServiceImpl implements BackendService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties properties;

    public String getEmpleadoById(String id) {
        return "Eder";
    }

    public LoginResponse getToken() {
        HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("admin","admin"));
        ResponseEntity<LoginResponse> response = restTemplate.exchange(properties.getRegistroUrl() + "/api/authenticate",
                                                                        HttpMethod.POST,
                                                                        request,
                                                                        LoginResponse.class);

        return response.getBody();
    }
}