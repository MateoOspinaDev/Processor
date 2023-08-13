package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorResponseDto {
    private String registrosCorrectos;
    private String registrosIncorrectos;
    private String registrosTotalesProcesados;
}
