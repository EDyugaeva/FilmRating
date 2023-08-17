package com.example.filmrating.services;

import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.model.User;
import com.example.filmrating.utills.SearchString;

import java.util.List;
import java.util.Optional;

public class UserService {


    public static Optional<User> findById(long id) {
        return DaoFactory.getInstance().getUserDao().findById(id);
    }

    public static int save(User user) {

        return DaoFactory.getInstance().getUserDao().save(user);
    }

    public static int update(User user)  {
        return DaoFactory.getInstance().getUserDao().update(user);
    }

    public static void delete(long id) {
        DaoFactory.getInstance().getUserDao().delete(id);

    }

    public static List<User> findTop5Users() {
        return DaoFactory.getInstance().getUserDao().findTop5Users();
    }

    public static List<User> findUserByNameOrLastName(String searchString) {
        String name = SearchString.divideString(searchString)[0];
        String lastName = SearchString.divideString(searchString)[1];

        return DaoFactory.getInstance().getUserDao().searchUserByNameOrLastName(name, lastName);

    }

    public static User loginUser(String email, String password) {
        return DaoFactory.getInstance().getUserDao().loginUser(email, password);
    }

    public static boolean validateUser(String email, String password) {
        return DaoFactory.getInstance().getUserDao().validateUser(email, password);
    }

}
