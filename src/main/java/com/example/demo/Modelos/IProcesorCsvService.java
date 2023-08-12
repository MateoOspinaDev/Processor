package com.example.demo.Modelos;


import com.example.demo.Dtos.ValidatorResponseDto;

import java.io.IOException;
import java.text.ParseException;

public interface IProcesorCsvService {
    public ValidatorResponseDto readCsvFile(String filePath)throws IOException , ParseException;

}
