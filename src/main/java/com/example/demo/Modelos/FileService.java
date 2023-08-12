package com.example.demo.Modelos;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    @Service
    public class FileService {

        public void processFile(String filePath) throws IOException {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Procesa cada línea del archivo, realiza validaciones, operaciones, etc.
                    System.out.println(line);
                }
            }
        }
    }


