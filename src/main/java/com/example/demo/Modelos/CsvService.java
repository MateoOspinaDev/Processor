package com.example.demo.Modelos;


import com.example.demo.Dtos.ValidatorDto;
import com.example.demo.Dtos.ValidatorResponseDto;
import com.example.demo.Servicios.IApiServicio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService implements IProcesorCsvService {

    private final IApiServicio apiServicio;

    @Autowired
    public CsvService(IApiServicio apiServicio) {
        this.apiServicio = apiServicio;
    }

    public ValidatorResponseDto readCsvFile(String filePath) throws IOException, ParseException {
        List<Persona> personas = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (CSVRecord csvRecord : csvParser) {
                Persona persona = new Persona();
                //persona.setIndex(Integer.parseInt(csvRecord.get("Index")));
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
        System.out.println(personas);
        ValidatorResponseDto validaciones = this.validarPersonas(personas);
        return validaciones;
    }

    private ValidatorResponseDto validarPersonas(List<Persona> registrosPersonas) {
        int registrosCorrectos = 0;
        int registrosIncorrectos = 0;
        int registrosTotalesProcesados = 0;

        for (Persona persona : registrosPersonas) {
            ValidatorDto respuestaValidacion = apiServicio.post("http://localhost:8081/validate-person", persona);
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



