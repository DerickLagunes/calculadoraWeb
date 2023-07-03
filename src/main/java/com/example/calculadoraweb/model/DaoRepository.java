package com.example.calculadoraweb.model;

import java.util.List;

public interface DaoRepository<T> {
    List<T> findAll(); //Select * from X;
    T findOne(int id);//Select * from X where id = paramID

    boolean update(int id, Usuario usr);

    boolean delete(int id); // delete from X where;
}
