package com.example.demo.Servicios;


import com.example.demo.Dtos.ValidatorDto;

public interface IApiServicio {
    public ValidatorDto post(String url, Object body);
}
