package se.team_management.models;

public class AuthRequst {

    private String userName;
    private String password;

    public AuthRequst(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AuthRequst() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
