package Services;

import model.Book;

import java.util.List;

public interface Book_Service {
    List<Book> getAll();

    Book SearcBooks(String books);

    boolean UpdateBooks(Book book);

    boolean DeleteBooks(Book bookid);

    void  Addbook (Book book);

}
