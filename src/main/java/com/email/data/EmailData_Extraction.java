package com.email.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EmailData_Extraction {
  

    public static HashMap<Integer, ArrayList<String>> readExcelToHashMap(String excelFilePath) {
        HashMap<Integer, ArrayList<String>> data = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int rowNum = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ArrayList<String> rowData = new ArrayList<>();

                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (cell == null) {
                        rowData.add("Null");
                    } else {
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.add(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    rowData.add(cell.getDateCellValue().toString());
                                } else {
                                    rowData.add(String.valueOf(cell.getNumericCellValue()));
                                }
                                break;
                            case BOOLEAN:
                                rowData.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case FORMULA:
                                rowData.add(cell.getCellFormula());
                                break;
                            default:
                                rowData.add("Null");
                        }
                    }
                }
                data.put(rowNum, rowData);
                rowNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
