package pl.olapp.chat.dao;

import pl.olapp.chat.dto.LoginUser;
import pl.olapp.chat.dto.NewUser;

public interface UserDAO {
    void createUser(NewUser user);
    boolean loginUser(LoginUser user);
    boolean userExist(NewUser user);
}
