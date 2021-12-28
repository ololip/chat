package pl.olapp.chat.services;

import org.springframework.stereotype.Service;
import pl.olapp.chat.dao.UserDAO;
import pl.olapp.chat.dto.LoginUser;
import pl.olapp.chat.dto.NewUser;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(NewUser user) {
        this.userDAO.createUser(user);
    }

    @Override
    public boolean loginUser(LoginUser user) {
        return this.userDAO.loginUser(user);
    }

    @Override
    public boolean userExist(NewUser user) {
        return this.userDAO.userExist(user);
    }
}
