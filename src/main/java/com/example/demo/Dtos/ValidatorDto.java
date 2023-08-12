package com.example.demo.Dtos;

public class ValidatorDto {
    private Boolean esValido;

    public ValidatorDto(Boolean esValido) {
        this.esValido = esValido;
    }

    public Boolean getEsValido() {
        return esValido;
    }

    public void setEsValido(Boolean esValido) {
        this.esValido = esValido;
    }
}
