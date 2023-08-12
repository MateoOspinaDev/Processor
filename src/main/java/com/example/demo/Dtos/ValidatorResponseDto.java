package com.example.demo.Dtos;

public class ValidatorResponseDto {
    private String registrosCorrectos;
    private String registrosIncorrectos;
    private String registrosTotalesProcesados;

    public ValidatorResponseDto(String registrosCorrectos, String registrosIncorrectos, String registrosTotalesProcesados) {
        this.registrosCorrectos = registrosCorrectos;
        this.registrosIncorrectos = registrosIncorrectos;
        this.registrosTotalesProcesados = registrosTotalesProcesados;
    }

    public String getRegistrosCorrectos() {
        return registrosCorrectos;
    }

    public void setRegistrosCorrectos(String registrosCorrectos) {
        this.registrosCorrectos = registrosCorrectos;
    }

    public String getRegistrosIncorrectos() {
        return registrosIncorrectos;
    }

    public void setRegistrosIncorrectos(String registrosIncorrectos) {
        this.registrosIncorrectos = registrosIncorrectos;
    }

    public String getRegistrosTotalesProcesados() {
        return registrosTotalesProcesados;
    }

    public void setRegistrosTotalesProcesados(String registrosTotalesProcesados) {
        this.registrosTotalesProcesados = registrosTotalesProcesados;
    }
}
