
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class staff extends User implements UserInterface{
    // Define instance variables
    private String name;
    private String skill;
    private String qualification;
    private ArrayList<staff> matchedStaffList = new ArrayList<>();
    private ArrayList<staff> matchedStaffTrainingList = new ArrayList<>();
    private User user;
    private training t;

    // Constructor with name parameter
    public staff(String name) {
        this.name = name;
    }

    // Constructor with name, skill, and qualification parameters
    public staff(String name, String skill, String qualification) {
        this.skill = skill;
        this.qualification = qualification;
        this.name = name;
    }

    // Constructor with name and training parameters
    public staff(String name, training t) {
        this.t = t;
        this.name = name;
    }

    // Getter and setter method for name
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter method for skill
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    // Getter and setter method for qualification
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    // Getter method for training
    public String getTraining() {
        return t.getType() + ", " + t.getDuration() + "month";
    }

    // Method to receive list of matched staff members from administrator
    public void receiveMatchedStaffList(ArrayList<staff> matchedStaffList) {
        this.matchedStaffList.addAll(matchedStaffList);
    }

    // Method to receive list of matched staff members and their training from administrator
    public void receiveMatchedStaffTrainingList(ArrayList<staff> matchedStaffTrainingList) {
        this.matchedStaffTrainingList.addAll(matchedStaffTrainingList);
    }

    // Method to check if staff member has been selected
    public boolean viewselection(String name) {
        for (staff s : matchedStaffList) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Method to view staff member's training
    public void viewtraining(String name) {
        System.out.println("This is your current training situation:");
        for (staff s : matchedStaffTrainingList) {
            if (s.getName().equals(name)) {
                System.out.println(s.getName() + ", " + s.getTraining());
            }
        }
    }

    // Override createrole method to set user credentials
    @Override
    public void createrole(User user) throws IOException {
        user.setCredentials(name, name + "@gla.uk", "Password", "Staff");
    }
}