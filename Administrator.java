package ae;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class Administrator extends User {
    private static ArrayList<teachingRequirement> requirements = new ArrayList<>();
    private ArrayList<staff> matchedStaffList = new ArrayList<>();
    private ArrayList<staff> matchedStaffTrainingList = new ArrayList<>();
    private User user;

    user.setCredentials("IAmAdministrator", "admin@gla.uk", "Password", "Administrator");

    public void receiveTeachingRequirements(ArrayList<teachingRequirement> requirements) {
        this.requirements.addAll(requirements);
    }

    public void viewTeachingRequirements() {
        System.out.println("Current teaching requirements:");
        for (teachingRequirement requirement : requirements) {
            System.out.println(requirement.getSkill() + "\t" + requirement.getQualification());
        }
    }


    public void findstaff() throws FileNotFoundException {
        int rowCount = 0;
        HSSFWorkbook hssfWorkbook1 = new HSSFWorkbook();
        HSSFSheet hssfSheet1 = hssfWorkbook1.createSheet("RebuildStaff");
        for (teachingRequirement requirement : requirements) {
            String[] field = {requirement.getSkill(), requirement.getQualification()};

            HSSFWorkbook hssfWorkbook = null;
            try {
                hssfWorkbook = new HSSFWorkbook(new FileInputStream("/Users/wangyuxin/Desktop/staff.xls"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            for (Row row : hssfSheet) {
                if (row != null) {

                    int count = 0;
                    for (int i = 1; i < row.getLastCellNum(); i++) {
                        for (int j = 0; j < field.length; j++) {
                            if (field[j].equals(row.getCell(i).getStringCellValue())) count = count + 1;
                        }
                    }
                    if (count == field.length) {

                        HSSFRow hssfRow1 = hssfSheet1.createRow(rowCount);
                        rowCount = rowCount + 1;
                        for (int m = 0; m < field.length; m++) {
                            hssfRow1.createCell(m).setCellValue(field[m]);
                        }
                        hssfRow1.createCell(field.length).setCellValue(row.getCell(0).getStringCellValue());

                        String name = row.getCell(0).getStringCellValue();
                        staff matchedStaff = new staff(name, field[0], field[1]);
                        matchedStaffList.add(matchedStaff);
                    }
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream("/Users/wangyuxin/Desktop/RebuildStaff.xls");

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
            viewStaff();
        }
    }

    public void viewStaff() {
        System.out.println("Matched staff:");
        for (staff matchedStaff : matchedStaffList) {
            System.out.println(matchedStaff.getName() + ", " + matchedStaff.getSkill() + ", " + matchedStaff.getQualification());
        }
    }

        public void organizeTraining() throws IOException {
            HSSFWorkbook hssfWorkbook = null;
            try {
                hssfWorkbook = new HSSFWorkbook(new FileInputStream("/Users/wangyuxin/Desktop/RebuildStaff.xls"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            for (Row row : hssfSheet) {
                String type = row.getCell(0).getStringCellValue();
                int courseDuration = row.getCell(1).getStringCellValue().equals("undergraduate") ? 3 : 1;

                training t = new training(type, courseDuration);
                staff s = new staff(row.getCell(2).getStringCellValue(), t);
                matchedStaffTrainingList.add(s);
                row.createCell(row.getLastCellNum()).setCellValue(type + " training");
                row.createCell(row.getLastCellNum()).setCellValue(courseDuration+ " month");
            }
            FileOutputStream fileOut = new FileOutputStream("/Users/wangyuxin/Desktop/RebuildStaff.xls");
            hssfWorkbook.write(fileOut);
            fileOut.close();
            viewStaffAndTraining();


        }
    public void viewStaffAndTraining() {
        System.out.println("Matched staff and training:");
        for (staff matchedStaff : matchedStaffTrainingList) {
            System.out.println(matchedStaff.getName() + ", " + matchedStaff.getTraining());
        }
    }
    public ArrayList<staff> getmatchedStaffList() {
        return matchedStaffList;
    }
    public ArrayList<staff> getmatchedStaffTrainingList() {
        return matchedStaffTrainingList;
    }
}