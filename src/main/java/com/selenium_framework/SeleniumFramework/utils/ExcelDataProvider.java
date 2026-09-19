package com.selenium_framework.SeleniumFramework.utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.*;

public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public static Object[][] excelData(Method testMethod) {
        ExcelSource[] sources = testMethod.getAnnotationsByType(ExcelSource.class);
        if (sources.length == 0) {
            throw new RuntimeException(
                "Test method '" + testMethod.getName() + "' needs at least one @ExcelSource"
            );
        }

        // Har @ExcelSource se corresponding sheet padho
        List<List<Map<String, String>>> allSheetsData = new ArrayList<>();
        for (ExcelSource src : sources) {
            String filePath = "testdata/" + src.file();
            allSheetsData.add(ExcelReader.readSheetAsMap(filePath, src.sheet()));
        }

        int rowCount = allSheetsData.get(0).size();
        for (List<Map<String, String>> sheetRows : allSheetsData) {
            if (sheetRows.size() != rowCount) {
                throw new RuntimeException(
                    "All @ExcelSource sheets on '" + testMethod.getName() +
                    "' must have the same row count to merge row-by-row"
                );
            }
        }

        // Row-index ke hisaab se sab sheets ka row-i ek single Map mein merge karo
        Object[][] data = new Object[rowCount][1];
        for (int i = 0; i < rowCount; i++) {
            Map<String, String> merged = new LinkedHashMap<>();
            for (List<Map<String, String>> sheetRows : allSheetsData) {
                merged.putAll(sheetRows.get(i));
            }
            data[i][0] = merged;
        }
        return data;
    }
}