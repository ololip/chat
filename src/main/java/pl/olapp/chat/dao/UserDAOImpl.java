package pl.olapp.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.olapp.chat.dto.LoginUser;
import pl.olapp.chat.dto.NewUser;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements UserDAO{
    private final DataSource dataSource;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createUser(NewUser user) {
        String sql = "Insert into Users (login, password) " + "values(?,?)";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, bCryptPasswordEncoder.encode(user.getPassword()));
            preparedStatement.execute();
            connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    System.out.print("Exception in closing connection!");
                }
            }
        }
    }

    @Override
    public boolean loginUser(LoginUser user) {
        boolean userExist=false;
        String sql = "select login, password from Users where login = ?";
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() && bCryptPasswordEncoder.matches(user.getPassword(), resultSet.getString("password"))){
                userExist = true;
            }
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    System.out.print("Exception in closing connection!");
                }
            }
        }
        return userExist;
    }

    @Override
    public boolean userExist(NewUser user) {
        boolean userExist=false;
        String sql = "select login from Users where login = ?";
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userExist = true;
            }
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    System.out.print("Exception in closing connection!");
                }
            }
        }
        return userExist;
    }
}
