package com.example.demo.Servicios;

import com.example.demo.Dtos.ValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServicio implements IApiServicio {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiServicio(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ValidatorDto post(String url, Object body) {
        return restTemplate.postForObject(url, body, ValidatorDto.class);
    }
}
