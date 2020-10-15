package dao;
import metier.Categorie;

import java.util.ArrayList;

public interface CategorieDAO extends InterfaceDAO<Categorie> {

    public abstract ArrayList<Categorie> getByTitre(String titre);
    public abstract ArrayList<Categorie> getByVisuel(String visuel);

}
