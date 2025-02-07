package servicess.custom;

import model.Book;
import servicess.Super_Service;

import java.util.List;

public interface Book_service extends Super_Service {
    List<Book> getAll();
    Book SearcBooks(String books);
    boolean UpdateBooks(Book book);
    boolean DeleteBooks(Book bookid);
    boolean Addbook (Book book);
}
