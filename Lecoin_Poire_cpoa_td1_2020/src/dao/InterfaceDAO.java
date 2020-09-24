package dao;

import java.util.ArrayList;

public interface InterfaceDAO<T> {

    public abstract T getById(int id);
    public abstract boolean create(T object);
    public abstract boolean update(T object);
    public abstract boolean delete(T object);
    public abstract ArrayList<T> findAll();

}
