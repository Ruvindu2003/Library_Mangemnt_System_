package Services;

import model.Return_Book;

import java.util.List;

public interface Return_Books_Service {

    boolean addBooks (Return_Book returnBook);
    List<Return_Book> getAll();
}
