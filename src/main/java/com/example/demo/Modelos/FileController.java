package com.example.demo.Modelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class FileController {

        private final FileService fileService;

        @Autowired
        public FileController(FileService fileService) {
            this.fileService = fileService;
        }

        @PostMapping("/process-file")
        public ResponseEntity<String> processFile(@RequestParam("filePath") String filePath) {
            try {
                fileService.processFile(filePath);
                return new ResponseEntity<>("Archivo procesado exitosamente.", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error al procesar el archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


