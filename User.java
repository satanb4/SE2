//package ae;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class User {

    private String email;
    private String password;
    private String name;
    private String role;

    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.name;
    }

    public String getRole(){
        return this.role;
    }

    public void setCredentials(String name, String email, String password, String role) throws IOException {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        writeToFile();
    }

    private void writeToFile() throws IOException {
 /*       HSSFWorkbook hssfUserBook = new HSSFWorkbook();
        HSSFSheet hssfSheet1 = hssfUserBook.createSheet("UserBook");

        hssfUserBook = null;
        try {
            hssfUserBook = new HSSFWorkbook(new FileInputStream("/Users/wangyuxin/Desktop/user.xls"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HSSFSheet hssfSheet = hssfUserBook.getSheetAt(0);

        for (Row row : hssfSheet1) {
             */
        String filename = "/Users/wangyuxin/Desktop/user.xls";
        HSSFWorkbook workbook;

        try (FileInputStream file = new FileInputStream(filename)) {
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            // if the file does not exist, create a new workbook
            workbook = new HSSFWorkbook();
        }

        HSSFSheet sheet = workbook.getSheet("User");
        if (sheet == null) {
            // if the sheet does not exist, create a new one
            sheet = workbook.createSheet("User");
        }
        int rowNum = sheet.getLastRowNum() + 1;
        HSSFRow row = sheet.createRow(rowNum);

            String name = this.name;
            String email = this.email;
            String password = this.password;
            String role = this.role;
            row.createCell(row.getLastCellNum()+1).setCellValue(name);
            row.createCell(row.getLastCellNum()).setCellValue(email);
            row.createCell(row.getLastCellNum()).setCellValue(password);
            row.createCell(row.getLastCellNum()).setCellValue(role);
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        try {
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}