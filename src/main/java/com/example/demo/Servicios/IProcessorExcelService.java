package com.example.demo.Servicios;

import com.example.demo.Dtos.ValidatorResponseDto;
import com.example.demo.Modelos.DataSafety;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IProcessorExcelService {
  public ValidatorResponseDto readExcelFile(String filePath) throws IOException, ParseException;
}
