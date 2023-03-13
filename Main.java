package ae;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        String staffname;

        classDirector director = new classDirector();
        Administrator administrator = new Administrator();

        while (true) {
            System.out.println("Please enter your option:");
            System.out.println("1. Class Director");
            System.out.println("2. Administrator");
            System.out.println("3. Staff");
            System.out.println("0. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    director.fillTeachingRequirement();
                    administrator.receiveTeachingRequirements(director.getRequirements());
                    break;
                case 2:
                    administrator.viewTeachingRequirements();
                    administrator.findstaff();
                    administrator.organizeTraining();
                    break;
                case 3:
                    System.out.println("Please enter your name:");
                    staffname =scanner.next();
                    staff s = new staff(staffname);
                    s.receiveMatchedStaffList(administrator.getmatchedStaffList());
                    s.receiveMatchedStaffTrainingList(administrator.getmatchedStaffTrainingList());
                    s.viewselection(staffname);
                    if(s.viewselection(staffname) == true) {
                        System.out.println("Congratulations！You have been selected!");
                        s.viewtraining(staffname);
                     }
                    else System.out.println("Sorry, you were not selected.");
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}

