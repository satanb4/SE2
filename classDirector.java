package ae;

import java.util.ArrayList;
import java.util.Scanner;

public class classDirector extends User {
    private ArrayList<teachingRequirement> requirements = new ArrayList<>();



    public void fillTeachingRequirement() {
        System.out.println("Please enter the teaching requirement");
        System.out.println("Each requirement needs to fill in two fields, field 1 is the required skill, e.g. java and field 2 is the required qualification, e.g. postgraduate，each fields is separated by a tab key");
        System.out.println("Please input the enter key to enter new requirements, input exit to end");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String teachingReq = sc.nextLine();
            if (teachingReq.equals("exit")) break;
            String[] field = teachingReq.split("\t");
            if (field.length != 2) {
                System.out.println("Invalid input, please try again");
                continue;
            }
            teachingRequirement requirement = new teachingRequirement(field[0], field[1]);
            requirements.add(requirement);
            viewRequirements();
        }
    }

    public void viewRequirements() {
        System.out.println("Current teaching requirements:");
        for (teachingRequirement requirement : requirements) {
            System.out.println(requirement.getSkill() + "\t" + requirement.getQualification());
        }
    }

    public ArrayList<teachingRequirement> getRequirements() {
        return requirements;
    }

}

