package com.example.kinorate.dao;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<T> {

    int save(T obj);

    Optional<T> findById(long id);

    List<T> getAll() ;

    int update(T obj) ;

    void delete(long id) ;


}
