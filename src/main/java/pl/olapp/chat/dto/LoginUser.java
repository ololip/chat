package pl.olapp.chat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUser {
    @NotNull
    @Size(min = 3, max = 254)
    private String login;
    @NotNull
    @Size(min = 3, max = 254)
    private String password;

    @Override
    public String toString() {
        return "LoginUser{" +
                "login='" + login + '\'' +
                '}';
    }

    public LoginUser() {
    }

    public LoginUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
