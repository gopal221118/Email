package com.email.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmailID_List {
    public static HashMap<Integer, String> getMailNPWD(String excelFilePath) {
        String filePath = excelFilePath;
        HashMap<Integer, String> dataMap = new HashMap<Integer, String>();

        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
             Sheet sheet = workbook.getSheetAt(0);  // assuming we're reading the first sheet
            int rowNum = 0;
            for (Row row : sheet) {
                Cell cell0 = row.getCell(0);
                Cell cell1 = row.getCell(1);

                if (cell0 != null && cell1 != null) {
                    String value0 = cell0.toString();
                    String value1 = cell1.toString();
                    dataMap.put(rowNum, value0 + "~" + value1);
                }
                rowNum++;
            }
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    
		return dataMap;
    }
}
