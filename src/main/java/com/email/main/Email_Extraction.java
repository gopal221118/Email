package com.email.main;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Email_Extraction {
    public static void main(String[] args) {
        String inputXlsxFilePath = "C:\\Whatsapp\\Insurance\\Emails\\RAW\\State_EmailD.xlsx";
        String outputXlsxFilePath = "C:\\Whatsapp\\Insurance\\Emails\\RAWX\\State_Emails_D.xlsx";

        try {
            // Step 1: Read the XLSX file and store the data in a HashMap
            Map<String, List<String>> dataMap = readXLSXToMap(inputXlsxFilePath);

            // Step 2: Write the data from the HashMap to a new XLSX sheet
            writeMapToXLSX(dataMap, outputXlsxFilePath);

            System.out.println("Conversion completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<String>> readXLSXToMap(String xlsxFilePath) throws IOException {
        Map<String, List<String>> dataMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(xlsxFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getCell(0) != null) {
                    String key = row.getCell(0).getStringCellValue();
                    List<String> valueList = new ArrayList<>();
                    for (int i = 1; i < row.getLastCellNum(); i++) {
                        Cell cell = row.getCell(i);
                        if (cell != null) {
                            valueList.add(cell.toString());
                        } else {
                            valueList.add("");
                        }
                    }
                    dataMap.put(key, valueList);
                }
            }
        }
        return dataMap;
    }

    private static void writeMapToXLSX(Map<String, List<String>> dataMap, String xlsxFilePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        int rowNum = 0;
        for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(entry.getKey());

            List<String> valueList = entry.getValue();
            for (int i = 0; i < valueList.size(); i++) {
                cell = row.createCell(i + 1);
                cell.setCellValue(valueList.get(i));
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(xlsxFilePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}