package dao;
import metier.Categorie;

import java.util.ArrayList;

public interface CategorieDAO extends InterfaceDAO<Categorie> {

    public abstract Categorie getById(int id);
    public abstract boolean create(Categorie categorie);
    public abstract boolean update(Categorie categorie);
    public abstract boolean delete(Categorie categorie);

    public abstract ArrayList<Categorie> getByTitre(String titre);
    public abstract ArrayList<Categorie> getByVisuel(String visuel);
    public abstract ArrayList<Categorie> listCategorie();
    
}
