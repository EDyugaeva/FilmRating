package com.example.filmrating.dao;

import com.example.filmrating.dao.impl.CommentDaoImpl;
import com.example.filmrating.dao.impl.FilmDaoImpl;
import com.example.filmrating.dao.impl.RateDaoImpl;
import com.example.filmrating.dao.impl.UserDaoImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();
    private UserDao userDao;
    private RateDao rateDao;
    private FilmDao filmDao;
    private CommentDao commentDao;

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
