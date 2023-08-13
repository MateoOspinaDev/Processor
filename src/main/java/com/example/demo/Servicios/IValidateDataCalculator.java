package com.example.demo.Servicios;

import com.example.demo.Dtos.ValidatorResponseDto;

import java.util.List;

public interface IValidateDataCalculator<T> {
    ValidatorResponseDto validarRegistros(List<T> registros, String urlValidacion);
}
