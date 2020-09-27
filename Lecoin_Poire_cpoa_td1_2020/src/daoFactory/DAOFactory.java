package daoFactory;

import dao.CategorieDAO;
import dao.ClientDAO;
import dao.ProduitDAO;
import dao.enumeration.Persistence;

public abstract class DAOFactory {

	public static DAOFactory getDaoFactory(Persistence cible) {

        DAOFactory daoFactory = null;

        switch(cible){
            case MYSQL:
                daoFactory = new MySQLDAOFactory();
                break;
            case LISTEMEMOIRE:
                daoFactory = new ListeMemoireDAOFactory();
                break;
        }
        return daoFactory;
    }

    public abstract CategorieDAO getCategorieDAO();
    public abstract ClientDAO getClientDAO();
    public abstract ProduitDAO getProduitDAO();
}
