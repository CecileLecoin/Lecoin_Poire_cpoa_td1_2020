package dao;

public interface InterfaceDAO<T> {

    public abstract T getById(int id);
    public abstract boolean create(T object);
    public abstract boolean update(T object);
    public abstract boolean delete(T object);
    
}
