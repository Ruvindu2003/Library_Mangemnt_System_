package repository;

import repository.custom.impl.Book_Repository_impl;
import repository.custom.impl.Member_Repository_impl;
import repository.custom.impl.Staff_Repository_impl;
import utill.RepositoryType;

public class Repository_Factory {
    private static Repository_Factory instance;
    private Repository_Factory() {}

    public static Repository_Factory getInstance() {
        return instance == null ? instance = new Repository_Factory() : instance;
    }

    public <T extends Super_Repository> T getRepository_Factory(RepositoryType type) {
        switch (type) {
            case Book: return (T) new Book_Repository_impl();
            case Member: return (T) new Member_Repository_impl();
            case Staff: return (T) new Staff_Repository_impl();
        }
        return null;
    }
}
