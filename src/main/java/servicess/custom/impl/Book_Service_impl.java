package servicess.custom.impl;

import model.Book;
import model.Members;
import repository.Repository_Factory;
import repository.custom.Book_Repository;
import servicess.custom.Book_service;
import utill.RepositoryType;

import java.util.List;

public class Book_Service_impl implements Book_service {

    Book_Repository bookRepository = Repository_Factory.getInstance().getRepository_Factory(RepositoryType.Book);

    @Override
    public List<Book> getAll() {
        List<Book>all=bookRepository.getAll();


        return all;
    }

    @Override
    public Book SearcBooks(String books) {
        Book search = bookRepository.SerachBook(books);
        return search;
    }


    @Override
    public boolean UpdateBooks(Book book) {
        boolean update =bookRepository.update(book);

        return update;
    }

    @Override
    public boolean DeleteBooks(Book bookid) {
        return false;
    }

    @Override
    public boolean Addbook(Book book) {
        Book add = bookRepository.add(book);
        if (add != null) {
            return true;
        }else {
            return  false;
        }
    }
}
