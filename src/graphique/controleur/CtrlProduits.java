package graphique.controleur;

import dao.enumeration.Persistence;
import daoFactory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import metier.Categorie;
import metier.Produit;

import java.net.URL;
import java.util.ResourceBundle;

public class CtrlProduits implements Initializable {

    @FXML
    private Label labelAffiche;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private ChoiceBox<Categorie> choiceBoxCategorie;
    @FXML
    private Button btnCreer;
    @FXML
    private TextField textFieldTarif;
    @FXML
    private TextField textFieldNom;
    
    private DAOFactory dao;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE);
        dao.getCategorieDAO().create(new Categorie(0, "titre", "visuel"));
        this.choiceBoxCategorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
    }

    public void creerProduit() {
    	
    	Produit produit = new Produit();
    	Categorie categorie = this.choiceBoxCategorie.getValue();
    	
    	try {
    		String nom = this.textFieldNom.getText().trim();
    		produit.setNom(nom);    		
    	} catch (IllegalArgumentException e){
    		this.labelAffiche.setText(e.getMessage());
    	}
    	try {
    		String description = this.textAreaDescription.getText().trim();
    		produit.setDescription(description);    		
    	} catch (IllegalArgumentException e){
    		this.labelAffiche.setText(e.getMessage());
    	}
    	try {
    		float tarif = Float.parseFloat(this.textFieldTarif.getText().trim());
    		produit.setTarif(tarif);    		
    	} catch (IllegalArgumentException e){
    		this.labelAffiche.setText(e.getMessage());
    	}
    	if (categorie == null) {
    		this.labelAffiche.setText("Aucune catégorie séléctionner");
    	}
    	else {
    		produit.setCategorie(categorie);    		
    	}

    	this.labelAffiche.setText(produit.toString());
    	dao.getProduitDAO().create(produit);
    }
}
