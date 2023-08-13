package com.example.demo.Servicios.Implementations;

import com.example.demo.Dtos.ValidatorDto;
import com.example.demo.Dtos.ValidatorResponseDto;
import com.example.demo.Servicios.IApiServicio;
import com.example.demo.Servicios.IValidateDataCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class validateDataCalculatorImp implements IValidateDataCalculator {
    private final IApiServicio apiServicio;

    @Autowired
    public validateDataCalculatorImp(IApiServicio apiServicio) {
        this.apiServicio = apiServicio;
    }

    @Override
    public ValidatorResponseDto validarRegistros(List registros, String urlValidacion) {
        int registrosCorrectos = 0;
        int registrosIncorrectos = 0;
        int registrosTotalesProcesados = 0;

        for (Object registro : registros) {
            ValidatorDto respuestaValidacion = apiServicio.post(urlValidacion, registro);
            if (respuestaValidacion.getEsValido()) {
                registrosCorrectos++;
            } else {
                registrosIncorrectos++;
            }
            registrosTotalesProcesados++;
        }

        return new ValidatorResponseDto(
                String.valueOf(registrosCorrectos),
                String.valueOf(registrosIncorrectos),
                String.valueOf(registrosTotalesProcesados)
        );
    }
}
