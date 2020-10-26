package daofactory;

import dao.CategorieDAO;
import dao.ClientDAO;
import dao.CommandeDAO;
import dao.ProduitDAO;
import dao.listememoire.ListeMemoireCategorieDAO;
import dao.listememoire.ListeMemoireClientDAO;
import dao.listememoire.ListeMemoireCommandeDAO;
import dao.listememoire.ListeMemoireProduitDAO;
import exceptions.CommandeApplicationException;

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

    @Override
    public CommandeDAO getCommandeDAO() throws CommandeApplicationException {
        return ListeMemoireCommandeDAO.getInstance();
    }
}
