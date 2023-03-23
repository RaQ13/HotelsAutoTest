package patterns.pl.travelers.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static void readExcel(String fileName) throws IOException {

        File file = new File("src/test/resources/" + fileName);
        FileInputStream inputStream = new FileInputStream(file);

        // w zależności od rozszerzenia pliku będzie się różniła implementacja
        // inicjacja
        Workbook workbook = null;
        String fileExt = fileName.substring(fileName.indexOf("."));

        System.out.println(fileExt);

        //implementacja workbook zależna od rozszerzenia pliku excela
        if (fileExt.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExt.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }

        Sheet sheet = workbook.getSheetAt(0);

        //ilość wierszy
        int rowCount = sheet.getLastRowNum();

        //od 1, pomijany pierwszy wiersz, który jest nagłówkami
        for (int i = 1; i <= rowCount; i++) {
            //rząd
            Row row = sheet.getRow(i);
            //komórka w rzędzie
            System.out.println(row.getCell(0).getStringCellValue());
            System.out.println(row.getCell(1).getStringCellValue());
            row.getCell(0).getStringCellValue();
            row.getCell(1).getStringCellValue();
        }
    }

    public static void main(String[] args) throws IOException {
        readExcel("testData.xlsx");
    }
}
