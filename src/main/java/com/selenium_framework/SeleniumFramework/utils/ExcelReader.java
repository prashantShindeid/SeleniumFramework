package com.selenium_framework.SeleniumFramework.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> readSheetAsMap(String classpathResource, String sheetName) {
        List<Map<String, String>> records = new ArrayList<>();

        try (InputStream is = ExcelReader.class.getClassLoader().getResourceAsStream(classpathResource)) {
            if (is == null) {
                throw new RuntimeException("Excel resource not found on classpath: " + classpathResource);
            }

            try (Workbook workbook = WorkbookFactory.create(is)) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new RuntimeException("Sheet not found: " + sheetName);
                }

                Row headerRow = sheet.getRow(0);
                List<String> headers = new ArrayList<>();
                for (Cell cell : headerRow) {
                    headers.add(cell.getStringCellValue().trim());
                }

                int rowCount = sheet.getPhysicalNumberOfRows();
                for (int i = 1; i < rowCount; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;

                    Map<String, String> rowData = new LinkedHashMap<>();
                    for (int col = 0; col < headers.size(); col++) {
                        Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        rowData.put(headers.get(col), getCellValueAsString(cell));
                    }
                    records.add(rowData);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel: " + classpathResource, e);
        }

        return records;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) return cell.getDateCellValue().toString();
                double val = cell.getNumericCellValue();
                return (val == Math.floor(val)) ? String.valueOf((long) val) : String.valueOf(val);
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default: return "";
        }
    }
}