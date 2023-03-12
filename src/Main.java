import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import User.User;

public static void main(String[] args) {
        User user = new User("test_user@gmail.com", "123456");
        System.out.println(user.getEmail());
}
