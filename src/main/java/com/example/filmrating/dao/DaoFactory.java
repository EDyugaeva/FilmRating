package com.example.filmrating.dao;

import com.example.filmrating.dao.impl.CommentDaoImpl;
import com.example.filmrating.dao.impl.FilmDaoImpl;
import com.example.filmrating.dao.impl.RateDaoImpl;
import com.example.filmrating.dao.impl.UserDaoImpl;
import lombok.Getter;
@Getter
public class DaoFactory {

    private  UserDao userDao;
    private  RateDao rateDao;
    private  FilmDao filmDao;
    private CommentDao commentDao;

    private static final DaoFactory INSTANCE = new DaoFactory();

    private DaoFactory() {
        try {
            this.userDao = new UserDaoImpl();
            this.rateDao = new RateDaoImpl();
            this.filmDao = new FilmDaoImpl();
            this.commentDao = new CommentDaoImpl();
        }
        catch (Exception e        ) {
            e.printStackTrace();
        }

    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

}
