import java.util.Scanner;

public class rolefactory {
    public static UserInterface getUser(int roleType){
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


