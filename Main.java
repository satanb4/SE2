
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;


        // Creating objects for user and rolefactory classes
        User user = new User();
        rolefactory factory = new rolefactory();


        // Creating objects for classDirector and administrator interfaces
        UserInterface classDirector = factory.getUser(1);
        UserInterface administrator = factory.getUser(2);

        // Main menu for user to select the appropriate option
        while (true) {
            System.out.println("Please enter your option:");
            System.out.println("1. Class Director");
            System.out.println("2. Administrator");
            System.out.println("3. Staff");
            System.out.println("0. Exit");

            option = scanner.nextInt();




            // Switch case to perform appropriate actions based on user's selection
            switch (option) {
                case 1:
                    classDirector.createrole(user);

                    // Allow Class Director to fill in teaching requirements
                    ((classDirector)classDirector).fillTeachingRequirement();
                    break;
                // Allow Administrator to receive teaching requirements from Class Director and find display matched staff, and organize training for matched staff
                case 2:
                    administrator.createrole(user);
                    ((Administrator) administrator).receiveTeachingRequirements( ((classDirector)classDirector).getRequirements());
                    ((Administrator) administrator).viewTeachingRequirements();
                    ((Administrator) administrator).findstaff();
                    ((Administrator) administrator).viewMatchedStaff();
                    ((Administrator) administrator).organizeTraining();
                    break;

                    //Receive matched stafflist and matched staff training list from Administrator and view selection and training for the selected staff
                case 3:
                    UserInterface staff = factory.getUser(option);
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
