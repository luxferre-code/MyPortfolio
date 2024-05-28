package fr.valentinthuillier.portfolio.dao;

import java.util.Map;

public interface IDao<T> {

    T find(int id);

    T[] findAll();

    boolean save(T object);

    boolean update(T object);

    boolean delete(T object);

    boolean deleteAll();

    int count();

}
