
import java.util.ArrayList;

//class definition for teachingRequirement
public class teachingRequirement {
    //private member variable to store required skill
    private String skill;
    //private member variable to store required qualification
    private String qualification;


    //method to get and set required skill
    public teachingRequirement(String skill, String qualification) {
        this.skill = skill;
        this.qualification = qualification;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    //method to get and set required qualification
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }


}

