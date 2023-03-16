import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// class definition for user
public class User {

    private String email;
    private String password;
    private String name;
    private String role;

    //Getter methods
    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.name;
    }

    public String getRole(){
        return this.role;
    }

    // Setter method to set the credentials of a user and write them to a file
    public void setCredentials(String name, String email, String password, String role) throws IOException {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        writeToFile(); // calls the writeToFile method to write the user credentials to a file
    }

    // Method to write user credentials to a file
    private void writeToFile() throws IOException {
        String filename = "/Users/gongchunliu/Desktop/SE/user.xls";
        HSSFWorkbook workbook;

        // Try to read an existing file, or create a new one if it doesn't exist
        try (FileInputStream file = new FileInputStream(filename)) {
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            workbook = new HSSFWorkbook();
        }

        // Get or create a sheet named "User"
        HSSFSheet sheet = workbook.getSheet("User");
        if (sheet == null) {
            // if the sheet does not exist, create a new one
            sheet = workbook.createSheet("User");
        }

        // Determine the next available row number and create a new row
        int rowNum = sheet.getLastRowNum() + 1;
        HSSFRow row = sheet.createRow(rowNum);

        // Set the cell values for the user credentials in the new row
            String name = this.name;
            String email = this.email;
            String password = this.password;
            String role = this.role;
            row.createCell(row.getLastCellNum()+1).setCellValue(name);
            row.createCell(row.getLastCellNum()).setCellValue(email);
            row.createCell(row.getLastCellNum()).setCellValue(password);
            row.createCell(row.getLastCellNum()).setCellValue(role);

        // Write the updated workbook to the file
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);

        // Close the output stream
        try {
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}