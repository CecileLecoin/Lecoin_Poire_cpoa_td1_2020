package daofactory;

import dao.CategorieDAO;
import dao.ClientDAO;
import dao.CommandeDAO;
import dao.ProduitDAO;
import dao.mysql.MySQLCategorieDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLCommandeDAO;
import dao.mysql.MySQLProduitDAO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public CategorieDAO getCategorieDAO() {
        return MySQLCategorieDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return MySQLClientDAO.getInstance();
    }

    @Override
    public ProduitDAO getProduitDAO() {
        return MySQLProduitDAO.getInstance();
    }

    @Override
    public CommandeDAO getCommandeDAO() {
        return MySQLCommandeDAO.getInstance();
    }
}
