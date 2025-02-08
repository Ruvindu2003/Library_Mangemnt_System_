package servicess.custom.impl;

import entity.Login_entity;
import repository.Repository_Factory;
import repository.custom.Login_Repository;
import servicess.custom.Login_Service;
import utill.RepositoryType;

import java.sql.SQLException;

public class Login_Service_impl implements Login_Service {

    Login_Repository loginRepository= Repository_Factory.getInstance().getRepository_Factory(RepositoryType.Login);
    @Override
    public Login_entity getUser(String user_name, String password) throws SQLException {
        Login_entity login =loginRepository.getUser(user_name,password);
        return  login;
    }
}
