package ae;


import java.util.ArrayList;

public class staff extends User {
    private String name;
    private String skill;
    private String qualification;
    private ArrayList<staff> matchedStaffList = new ArrayList<>();
    private ArrayList<staff> matchedStaffTrainingList = new ArrayList<>();
    private training t;

    public staff(String name) {
        this.name = name;
    }

    public staff(String name, String skill, String qualification) {
        this.skill = skill;
        this.qualification = qualification;
        this.name = name;
    }

    public staff(String name, training t) {
        this.t = t;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTraining() {
        return t.getType() + ", " + t.getDuration() + "month";
    }

    public void receiveMatchedStaffList(ArrayList<staff> matchedStaffList) {
        this.matchedStaffList.addAll(matchedStaffList);
    }

    public void receiveMatchedStaffTrainingList(ArrayList<staff> matchedStaffTrainingList) {
        this.matchedStaffTrainingList.addAll(matchedStaffTrainingList);
    }

    public boolean viewselection(String name) {
        for (staff s : matchedStaffList) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void viewtraining(String name) {
        System.out.println("This is your current training situation:");
        for (staff s : matchedStaffTrainingList) {
            if (s.getName().equals(name)) {
                System.out.println(s.getName() + ", " + s.getTraining());
            }
        }
    }
}
