package repository;

import entity.Login_entity;
import model.Book;
import model.Login;
import model.Members;
import model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface Crud_Repository<T>extends Super_Repository {

    Book add(T entity);
    boolean update(T entity);
    boolean delete(String id);
    Members Search(String entity);

    List<Book> getAll();
    Book SerachBook(String entity);
    List<Members> getAl();
    Members addMemeber(T entity);

    Staff addStaff(Staff entity);
    Staff searchStaff(String entity);
    List<Staff> getAllStaff();

    Login_entity getUser(String user_name , String password) throws SQLException;





}
