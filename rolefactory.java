import java.util.Scanner;

//class definition for rolefactory
public class rolefactory {
    //static method that returns a UserInterface object
    public static UserInterface getUser(int roleType){
        //create a new Scanner object to read input from console
        Scanner scanner = new Scanner(System.in);

        if(roleType==1){
            return new classDirector();
        } else if(roleType==2){
            return new Administrator();
        } else if(roleType==3){
            System.out.println("Please enter your name:");
            String staffname =scanner.next();
            return new staff(staffname);
        }
        return null;
    }
}


