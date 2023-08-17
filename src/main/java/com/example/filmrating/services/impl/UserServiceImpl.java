package com.example.filmrating.services.impl;

import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.dao.UserDao;
import com.example.filmrating.model.User;
import com.example.filmrating.services.UserService;
import com.example.filmrating.utills.SearchString;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    static UserDao dao = DaoFactory.getInstance().getUserDao();


    public  Optional<User> findById(long id) {
        return DaoFactory.getInstance().getUserDao().findById(id);
    }

    public  int save(User user) {
        return dao.save(user);
    }

    public  int update(User user)  {
        return dao.update(user);
    }

    public  void delete(long id) {
        dao.delete(id);

    }

    public  List<User> findTop5Users() {
        return dao.findTop5Users();
    }

    public  List<User> findUserByNameOrLastName(String searchString) {
        String name = SearchString.divideString(searchString)[0];
        String lastName = SearchString.divideString(searchString)[1];

        return dao.searchUserByNameOrLastName(name, lastName);

    }

    public  User loginUser(String email, String password) {
        return dao.loginUser(email, password);
    }

    public  boolean validateUser(String email, String password) {
        return dao.validateUser(email, password);
    }

}
