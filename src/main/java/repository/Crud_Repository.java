package repository;

import model.Book;
import model.Members;
import model.Staff;

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

    entity.Staff addStaff(T entity);
    Staff searchStaff(String entity);
    List<Staff> getAllStaff();





}
