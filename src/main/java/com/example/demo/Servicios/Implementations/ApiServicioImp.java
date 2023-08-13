package com.example.demo.Servicios.Implementations;

import com.example.demo.Dtos.ValidatorDto;
import com.example.demo.Servicios.IApiServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServicioImp implements IApiServicio {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiServicioImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ValidatorDto post(String url, Object body) {
        return restTemplate.postForObject(url, body, ValidatorDto.class);
    }
}
