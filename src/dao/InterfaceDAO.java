package dao;

import exceptions.CommandeApplicationException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceDAO<T> {

    public abstract T getById(int id) throws CommandeApplicationException;
    public abstract boolean create(T object) throws CommandeApplicationException;
    public abstract boolean update(T object) throws CommandeApplicationException;
    public abstract boolean delete(T object) throws CommandeApplicationException;
    public abstract ArrayList<T> findAll() throws CommandeApplicationException;

}
