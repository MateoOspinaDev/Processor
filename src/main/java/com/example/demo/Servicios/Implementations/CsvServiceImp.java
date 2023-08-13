package com.example.demo.Servicios.Implementations;

import com.example.demo.Dtos.ValidatorResponseDto;
import com.example.demo.Modelos.Persona;
import com.example.demo.Servicios.IProcesorCsvService;
import com.example.demo.Servicios.IValidateDataCalculator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvServiceImp implements IProcesorCsvService {

    private final IValidateDataCalculator<Persona> validatorService;

    @Value("${validation.api.url}")
    private String validationApiUrl;

    @Autowired
    public CsvServiceImp(IValidateDataCalculator<Persona> validatorService) {
        this.validatorService = validatorService;
    }

    public ValidatorResponseDto readCsvFile(String filePath) throws IOException, ParseException {
        List<Persona> personas = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                Persona persona = new Persona();
                persona.setUserId(csvRecord.get("User Id"));
                persona.setFirstName(csvRecord.get("First Name"));
                persona.setLastName(csvRecord.get("Last Name"));
                persona.setSex(csvRecord.get("Sex"));
                persona.setEmail(csvRecord.get("Email"));
                persona.setPhone(csvRecord.get("Phone"));
                LocalDate dateOfBirth = LocalDate.parse(csvRecord.get("Date of birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                persona.setDateOfBirth(dateOfBirth);
                persona.setJobTitle(csvRecord.get("Job Title"));
                personas.add(persona);
            }
        }
        return validatorService.validarRegistros(personas,validationApiUrl+"validate-person");
    }
}



