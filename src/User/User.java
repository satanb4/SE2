package User;


interface UserInterface {
    public String getEmail();
    public void setEmail(String email);
    public void setPassword(String password);
}

public class User {

    private String email;
    private String password;
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public static void main(String[] args) {
        User user = new User("sayanban@gmail.com", "123456");
        System.out.println(user.getEmail());
    }
}
