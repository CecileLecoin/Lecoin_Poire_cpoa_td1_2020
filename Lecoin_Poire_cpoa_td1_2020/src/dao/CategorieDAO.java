package dao;
import metier.Categorie;

import java.util.ArrayList;

public interface CategorieDAO extends InterfaceDAO<Categorie> {

    @Override
    public abstract Categorie getById(int id);
    @Override
    public abstract boolean create(Categorie categorie);
    @Override
    public abstract boolean update(Categorie categorie);
    @Override
    public abstract boolean delete(Categorie categorie);

    public abstract ArrayList<Categorie> getByTitre(String titre);
    public abstract ArrayList<Categorie> getByVisuel(String visuel);
    public abstract ArrayList<Categorie> listCategorie();
    
}
