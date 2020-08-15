package se.team_management.models;

public class AuthRequst {

    private String username;
    private String password;

    public AuthRequst(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthRequst() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
