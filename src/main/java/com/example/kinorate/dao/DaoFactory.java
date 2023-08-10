package com.example.kinorate.dao;

import com.example.kinorate.dao.impl.CommentDaoImpl;
import com.example.kinorate.dao.impl.FilmDaoImpl;
import com.example.kinorate.dao.impl.RateDaoImpl;
import com.example.kinorate.dao.impl.UserDaoImpl;
import lombok.Getter;
@Getter
public class DaoFactory {
    private final UserDao userDao;
    private final RateDao rateDao;
    private final FilmDao filmDao;
    private final CommentDao commentDao;

    private static final DaoFactory INSTANCE = new DaoFactory();

    private DaoFactory() {
        this.userDao = new UserDaoImpl();
        this.rateDao = new RateDaoImpl();
        this.filmDao = new FilmDaoImpl();
        this.commentDao = new CommentDaoImpl();


    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

}
