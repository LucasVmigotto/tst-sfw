package com.fatec.scel.dd;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ManipulaExcel {
    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook excelWBook;
    private static XSSFCell cell;
    private static XSSFRow row;
    private static String path;
    
    public static void setExcelFile(String path, String sheetName) throws Exception {
        try {
            System.out.println("manipula excel abre sheet= " + sheetName);
            FileInputStream ExcelFile = new FileInputStream(path);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println("erro manipula excel = " + e.getMessage());
            throw (e);
        }

    }
    
    public static String getCellData(int rowNum, int colNum) throws Exception {
        try {
            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.getStringCellValue();
            return cellData;
        } catch (Exception e) {
            return "";
        }
    }
    
    public static void setCellData(String result, int rowNum, int colNum) throws Exception {
        try {
            row = excelWSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(result);
            } else {
                cell.setCellValue(result);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            System.out.println("erro no set excel data = " + e.getMessage());
            throw (e);
        }
    }
}