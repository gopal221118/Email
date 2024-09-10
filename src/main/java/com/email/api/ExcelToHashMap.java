package com.email.api;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelToHashMap {
    public static HashMap readfromExcel(String excelFilePath) {
    	HashMap<Integer, List<String>> dataMap = new HashMap<Integer, List<String>>();
    	
    	try
    	{

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int rowNum = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ArrayList<String> cellValues = new ArrayList<String>();

                row.forEach(cell -> {
                	String cellValue = getCellValue(cell);
                    cellValues.add(cellValue);
                });

                dataMap.put(rowNum++, cellValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  	}
    	catch (Exception e) {
    		e.printStackTrace();
		}
		return dataMap;
    }

    private static String getCellValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }  else {
            return "UNKNOWN TYPE";
        }
    }
}

