package pl.olapp.chat.services;

import pl.olapp.chat.dto.LoginUser;
import pl.olapp.chat.dto.NewUser;

public interface UserService {
    void createUser(NewUser user);
    boolean loginUser(LoginUser user);
    boolean userExist(NewUser user);
}
