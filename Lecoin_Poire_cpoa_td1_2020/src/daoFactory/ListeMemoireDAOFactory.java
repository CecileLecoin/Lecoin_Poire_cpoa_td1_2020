package daoFactory;

import dao.CategorieDAO;
import dao.ClientDAO;
import dao.ProduitDAO;
import dao.listememoire.ListeMemoireCategorieDAO;
import dao.listememoire.ListeMemoireClientDAO;
import dao.listememoire.ListeMemoireProduitDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

    @Override
    public CategorieDAO getCategorieDAO() {
        return ListeMemoireCategorieDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return ListeMemoireClientDAO.getInstance();
    }

    @Override
    public ProduitDAO getProduitDAO() {
        return ListeMemoireProduitDAO.getInstance();
    }

}