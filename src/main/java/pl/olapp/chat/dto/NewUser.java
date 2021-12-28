package pl.olapp.chat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewUser {
    @NotNull
    @Size(min = 3, max = 254)
    private String login;
    @NotNull
    @Size(min = 3, max = 254)
    private String password;
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
