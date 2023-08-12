package com.example.demo.Modelos;

import com.example.demo.Dtos.ValidatorResponseDto;
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

    @Autowired
    public CsvController(IProcesorCsvService iprocesorCsvService) {
        this.iprocesorCsvService=iprocesorCsvService;
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
}

