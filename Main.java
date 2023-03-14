//package ae;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        //String staffname;
        User user = new User();
        rolefactory factory = new rolefactory();
        //classDirector director = new classDirector();
        //Administrator administrator = new Administrator();

        UserInterface classDirector = factory.getUser(1);
        UserInterface administrator = factory.getUser(2);

        while (true) {
            System.out.println("Please enter your option:");
            System.out.println("1. Class Director");
            System.out.println("2. Administrator");
            System.out.println("3. Staff");
            System.out.println("0. Exit");

            option = scanner.nextInt();


            //UserInterface user = factory.getUser(option);

            switch (option) {
                case 1:
                    classDirector.createrole(user);
                    ((classDirector)classDirector).fillTeachingRequirement();
                    break;
                case 2:
                    administrator.createrole(user);
                    ((Administrator) administrator).receiveTeachingRequirements( ((classDirector)classDirector).getRequirements());
                    ((Administrator) administrator).viewTeachingRequirements();
                    ((Administrator) administrator).findstaff();
                    ((Administrator) administrator).organizeTraining();
                    break;
                case 3:
                    UserInterface staff = factory.getUser(option);
                    //staffname =scanner.next();
                    staff.createrole(user);
                    ((staff) staff).receiveMatchedStaffList(((Administrator) administrator).getmatchedStaffList());
                    ((staff) staff).receiveMatchedStaffTrainingList(((Administrator) administrator).getmatchedStaffTrainingList());
                    ((staff) staff).viewselection(((staff) staff).getName());
                    if(((staff) staff).viewselection(((staff) staff).getName()) == true) {
                        System.out.println("Congratulations！You have been selected!");
                        ((staff) staff).viewtraining(((staff) staff).getName());
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
