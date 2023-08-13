package com.example.demo.Controllers;

import com.example.demo.Dtos.ValidatorResponseDto;
import com.example.demo.Servicios.IProcesorCsvService;
import com.example.demo.Servicios.IProcessorExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class CsvController {

    private final IProcesorCsvService iprocesorCsvService;
    private final IProcessorExcelService iprocesorExcelService;

    @Autowired
    public CsvController(IProcesorCsvService iprocesorCsvService, IProcessorExcelService iprocesorExcelService) {
        this.iprocesorCsvService=iprocesorCsvService;
        this.iprocesorExcelService = iprocesorExcelService;
    }

    @PostMapping("/process-csv")
    public ResponseEntity<ValidatorResponseDto> processCsv(@RequestParam("filePath") String filePath) throws IOException {
        try {
            ValidatorResponseDto personas = iprocesorCsvService.readCsvFile(filePath);
            return new ResponseEntity<>(personas, HttpStatus.OK);
        } catch (IOException | ParseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/process-excel")
    public ResponseEntity<ValidatorResponseDto> processExcel(@RequestParam("filePath") String filePath) throws IOException {
        try {
            ValidatorResponseDto personas = iprocesorExcelService.readExcelFile(filePath);
            return new ResponseEntity<>(personas, HttpStatus.OK);
        } catch (IOException | ParseException e) {
            throw new IOException(e.getMessage());
        }
    }
}

