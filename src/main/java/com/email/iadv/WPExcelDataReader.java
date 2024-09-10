package com.email.iadv;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WPExcelDataReader {
   public static HashMap<Integer, ArrayList<String>> readExcel(String filePath) throws IOException {
      FileInputStream excelFile = new FileInputStream(filePath);
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet sheet = workbook.getSheetAt(0);
      HashMap<Integer, ArrayList<String>> dataMap = new HashMap<>();
      Iterator<Row> iterator = sheet.iterator();

      while (iterator.hasNext()) {
         Row currentRow = iterator.next();
         int rowNum = currentRow.getRowNum();
         Iterator<Cell> cellIterator = currentRow.iterator();
         ArrayList<String> rowData = new ArrayList<>();

         while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            rowData.add(getCellValueAsString(currentCell));
         }

         dataMap.put(rowNum, rowData);
      }

      workbook.close();
      excelFile.close();
      return dataMap;
   }

   private static String getCellValueAsString(Cell cell) {
      CellType cellType = cell.getCellType();
      
      if (cellType == CellType.NUMERIC) {
         if (DateUtil.isCellDateFormatted(cell)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(cell.getDateCellValue());
         } else {
            DecimalFormat df = new DecimalFormat("#");
            return df.format(cell.getNumericCellValue());
         }
      } else if (cellType == CellType.STRING) {
         return cell.getStringCellValue();
      } else if (cellType == CellType.BOOLEAN) {
         return String.valueOf(cell.getBooleanCellValue());
      } else if (cellType == CellType.BLANK) {
         return "";
      } else {
         return "";
      }
   }

   public static String getCurrentDate() {
      LocalDate currentDate = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      return currentDate.format(formatter);
   }
}
