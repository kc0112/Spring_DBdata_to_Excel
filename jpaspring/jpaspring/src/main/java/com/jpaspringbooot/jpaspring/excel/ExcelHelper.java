package com.jpaspringbooot.jpaspring.excel;

import com.jpaspringbooot.jpaspring.user.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    public static String[] HEADERS = {
            "id",
            "name",
            "birthDate"
    };

    public static String SHEET_NAME = "user_data";

    public static ByteArrayInputStream dataToExcel (List<User> list) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            Row row = sheet.createRow(0);

            for(int i=0;i<HEADERS.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            int rowIndex = 1;

            for(User user:list){
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(user.getId());
                dataRow.createCell(1).setCellValue(user.getName());
                dataRow.createCell(2).setCellValue(user.getBirthDate());
            }

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to import data to excel.");
            return null;
        }finally {
            workbook.close();
            out.close();
        }
    }
}
