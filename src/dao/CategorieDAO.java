package dao;
import exceptions.CommandeApplicationException;
import metier.Categorie;

import java.util.ArrayList;

public interface CategorieDAO extends InterfaceDAO<Categorie> {

    public abstract ArrayList<Categorie> getByTitre(String titre) throws CommandeApplicationException;
    public abstract ArrayList<Categorie> getByVisuel(String visuel) throws CommandeApplicationException;

}
