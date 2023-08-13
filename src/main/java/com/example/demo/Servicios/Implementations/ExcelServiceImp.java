package com.example.demo.Servicios.Implementations;

import com.example.demo.Dtos.ValidatorResponseDto;
import com.example.demo.Modelos.DataSafety;
import com.example.demo.Servicios.IProcessorExcelService;
import com.example.demo.Servicios.IValidateDataCalculator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelServiceImp implements IProcessorExcelService {

    private final IValidateDataCalculator<DataSafety> validatorService;

    @Value("${validation.api.url}")
    private String validationApiUrl;

    @Autowired
    public ExcelServiceImp(IValidateDataCalculator<DataSafety> validatorService) {
        this.validatorService = validatorService;
    }

    public ValidatorResponseDto readExcelFile(String filePath) throws IOException, ParseException {
        List<DataSafety> dataSafetyList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            int rowIndex = 0;

            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                DataSafety dataSafety = new DataSafety();
                double numericValue = row.getCell(0).getNumericCellValue();
                Date date = DateUtil.getJavaDate(numericValue);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = dateFormat.format(date);
                dataSafety.setDate(formattedDate);
                dataSafety.setInjuryLocation(row.getCell(1).getStringCellValue());
                dataSafety.setGender(row.getCell(2).getStringCellValue());
                dataSafety.setAgeGroup(row.getCell(3).getStringCellValue());
                dataSafety.setIncidentType(row.getCell(4).getStringCellValue());
                Cell daysLostCell = row.getCell(5);
                if (daysLostCell.getCellType() == CellType.NUMERIC) {
                    String daysLostValue = String.valueOf(daysLostCell.getNumericCellValue());
                    dataSafety.setDaysLost(daysLostValue);
                } else if (daysLostCell.getCellType() == CellType.STRING) {
                    String daysLostValue = daysLostCell.getStringCellValue();
                    dataSafety.setDaysLost(daysLostValue);
                }
                dataSafety.setPlant(row.getCell(6).getStringCellValue());
                dataSafety.setReportType(row.getCell(7).getStringCellValue());
                dataSafety.setShift(row.getCell(8).getStringCellValue());
                dataSafety.setDepartment(row.getCell(9).getStringCellValue());
                Cell incidentCost = row.getCell(10);
                if (incidentCost.getCellType() == CellType.NUMERIC) {
                    String incidentCostValue = String.valueOf(incidentCost.getNumericCellValue());
                    dataSafety.setIncidentCost(incidentCostValue);
                } else if (incidentCost.getCellType() == CellType.STRING) {
                    String incidentCostValue = incidentCost.getStringCellValue();
                    dataSafety.setIncidentCost(incidentCostValue);
                }
                dataSafety.setWkDay(row.getCell(11).getStringCellValue());
                dataSafety.setMonth(String.valueOf(row.getCell(12).getNumericCellValue()));
                dataSafety.setYear(String.valueOf(row.getCell(13).getNumericCellValue()));
                dataSafetyList.add(dataSafety);
                rowIndex++;
            }
        }
        return validatorService.validarRegistros(dataSafetyList,validationApiUrl+"validate-injury");
    }
}
