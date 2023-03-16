
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class Administrator extends User implements UserInterface{
    //ArrayList to store teaching requirements
    private static ArrayList<teachingRequirement> requirements = new ArrayList<>();
    //ArrayList to store matched staff (without training)
    private ArrayList<staff> matchedStaffList = new ArrayList<>();
    //ArrayList to store matched staff (with training)
    private ArrayList<staff> matchedStaffTrainingList = new ArrayList<>();
    //User object for the administrator
    private User user;

    // Method to create credentials for the administrator
    public void createrole(User user) throws IOException {
        user.setCredentials("IAmAdministrator", "admin@gla.uk", "Password", "Administrator");
    }

    // Method to receive teaching requirements
    public void receiveTeachingRequirements(ArrayList<teachingRequirement> requirements) {
        this.requirements.addAll(requirements);
    }

    // Method to view current teaching requirements
    public void viewTeachingRequirements() {
        System.out.println("Current teaching requirements:");
        for (teachingRequirement requirement : requirements) {
            System.out.println(requirement.getSkill() + "\t" + requirement.getQualification());
        }
    }


    // Method to find staff based on teaching requirements
    public void findstaff() throws FileNotFoundException {
        int rowCount = 0;

        //creat a new workbook and sheet for storing matched staff
        HSSFWorkbook hssfWorkbook1 = new HSSFWorkbook();
        HSSFSheet hssfSheet1 = hssfWorkbook1.createSheet("RebuildStaff");

        //Iterate through each teaching requirement
        for (teachingRequirement requirement : requirements) {

            //Store the skill and qualification required for the current teaching requirement in an array
            String[] field = {requirement.getSkill(), requirement.getQualification()};

            //Read the existing staff file
            HSSFWorkbook hssfWorkbook = null;
            try {
                hssfWorkbook = new HSSFWorkbook(new FileInputStream("/Users/gongchunliu/Desktop/SE/staff.xls"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Get the first sheet from the staff file and iterate through each row
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            for (Row row : hssfSheet) {
                if (row != null) {

                    int count = 0;
                    // Iterate through each cell in the row (excluding the first cell which contains the staff member's name)
                    for (int i = 1; i < row.getLastCellNum(); i++) {
                        for (int j = 0; j < field.length; j++) {

                            // If the current field matches the value in the current cell, increment the count
                            if (field[j].equals(row.getCell(i).getStringCellValue())) count = count + 1;
                        }
                    }

                    // If the number of matched fields is equal to the number of fields required, add the staff member to the matched staff list and to the rebuilt staff sheet
                    if (count == field.length) {

                        HSSFRow hssfRow1 = hssfSheet1.createRow(rowCount);
                        rowCount = rowCount + 1;
                        for (int m = 0; m < field.length; m++) {
                            hssfRow1.createCell(m).setCellValue(field[m]);
                        }
                        hssfRow1.createCell(field.length).setCellValue(row.getCell(0).getStringCellValue());

                        // Create a new staff object for the matched staff member and add it to the matched staff list
                        String name = row.getCell(0).getStringCellValue();
                        staff matchedStaff = new staff(name, field[0], field[1]);
                        matchedStaffList.add(matchedStaff);
                    }
                }
            }

            // Write the matched staff to a new sheet in a new workbook
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/gongchunliu/Desktop/SE/RebuildStaff.xls");

            try {
                hssfWorkbook1.write(fileOutputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fileOutputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                hssfWorkbook1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // View the list of matched staff
    public void viewMatchedStaff() {
        System.out.println("Matched staff:");
        for (staff matchedStaff : matchedStaffList) {
            System.out.println(matchedStaff.getName() + ", " + matchedStaff.getSkill() + ", " + matchedStaff.getQualification());
        }
    }

    // Organize training for the matched staff and update the excel sheet
    public void organizeTraining() throws IOException {
        HSSFWorkbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(new FileInputStream("/Users/gongchunliu/Desktop/SE/RebuildStaff.xls"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

        // Iterate through each row of the excel sheet
        for (Row row : hssfSheet) {
            String type = row.getCell(0).getStringCellValue();
            int courseDuration = row.getCell(1).getStringCellValue().equals("undergraduate") ? 3 : 1;

            // Create a new training object and staff object
            training t = new training(type, courseDuration);
            staff s = new staff(row.getCell(2).getStringCellValue(), t);

            // Add the staff object to the matchedStaffTrainingList
            matchedStaffTrainingList.add(s);

            // Update the excel sheet with the assigned training and course duration
            row.createCell(row.getLastCellNum()).setCellValue(type + " training");
            row.createCell(row.getLastCellNum()).setCellValue(courseDuration+ " month");
        }

        // Write the updated excel sheet to the file
        FileOutputStream fileOut = new FileOutputStream("/Users/gongchunliu/Desktop/SE/RebuildStaff.xls");
        hssfWorkbook.write(fileOut);
        fileOut.close();

        // Call the viewStaffAndTraining method to display the updated staff list with their assigned training
        viewStaffAndTraining();


    }

    // View the list of matched staff with their assigned training
    public void viewStaffAndTraining() {
        System.out.println("Matched staff and training:");
        for (staff matchedStaff : matchedStaffTrainingList) {
            System.out.println(matchedStaff.getName() + ", " + matchedStaff.getTraining());
        }
    }

    // Getter method for matchedStaffList and matchedStaffTrainingList
    public ArrayList<staff> getmatchedStaffList() {
        return matchedStaffList;
    }
    public ArrayList<staff> getmatchedStaffTrainingList() {
        return matchedStaffTrainingList;
    }
}

