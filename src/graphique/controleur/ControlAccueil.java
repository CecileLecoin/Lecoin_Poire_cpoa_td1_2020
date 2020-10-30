package graphique.controleur;

public class ControlAccueil {

    private ControlMain controlMain;

    public ControlAccueil() {
        controlMain = ControlMain.getInstance();
    }

    public void retourPage() {

    }
    public void goToClient() {

        controlMain.push("/res/fxml/page/Client.fxml", "Gestion des clients");
    }

    public void goToProduit() {

        controlMain.push("/res/fxml/page/Produit.fxml", "Gestion des produits");
    }

    public void goToCommande() {

        controlMain.push("/res/fxml/page/Commande.fxml", "Gestion des commandes");
    }

    public void goToCategorie() {

    }

    private void load() {

    }
}
