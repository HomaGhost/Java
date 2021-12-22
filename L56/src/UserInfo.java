import java.io.Serializable;
public class UserInfo implements Serializable  {
    String login;
    String password;
    String role;
    public UserInfo(){

    }
    public UserInfo(String login, String password){
        this.login = login;
        this.password = password;
    }
    public UserInfo(String login, String password, String role){
        this.login = login;
        this.password = password;
        this.role = role;
    }
    public void SetLogin(String login){
        this.login = login;
    }
    public void SetPassword(String password){
        this.password = password;
    }
    public void SetRole(String role){
        this.role = role;
    }

    public String GetLogin(){
        return login;
    }
    public String GetPassword(){
        return password;
    }
    public String GetRole(){
        return role;
    }

    public UserInfo StringToUser(String str){
        String[] parts = str.split(";");
        UserInfo user = new UserInfo();
        user.login = parts[0];
        user.password = parts[1];
        user.role = parts[2];
        return user;
    }

    public String UserToString(){
        String stud_info = login + ";" + password + ";" + role;
        return stud_info;
    }
}
