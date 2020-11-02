package graphique.controleur;

import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.Main;
import metier.Categorie;
import metier.Produit;
import utils.MessageBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ControlManageProduit implements Initializable {

    @FXML
    private Label labelTitre;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private ChoiceBox<Categorie> choiceBoxCategorie;
    @FXML
    private TextField textFieldTarif;
    @FXML
    private TextField textFieldNom;
	@FXML
	private Button button_Add;

	private ObservableList<Categorie> categoriesList;

	private final ControlMain controlMain;
	private Consumer<Produit> consumer;

	public ControlManageProduit() {
		controlMain = ControlMain.getInstance();
	}

	private DAOFactory dao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = Main.getInstance().getDAO();
		try {
			categoriesList = FXCollections.observableList(dao.getCategorieDAO().findAll());
		} catch (CommandeApplicationException e) {
			e.printStackTrace();
		}

		choiceBoxCategorie.setItems(categoriesList);
		textFieldNom.textProperty().addListener((observable, oldValue, newValue) -> {
			textField_LimitSize(observable, oldValue, newValue, textFieldNom);
		});
		textFieldTarif.textProperty().addListener((observable, oldValue, newValue) -> {
			textField_LimitSize(observable, oldValue, newValue, textFieldTarif);
		});
	}


	public void button_Add_OnClick() {

    	Produit produit = new Produit();
    	Categorie categorie = this.choiceBoxCategorie.getValue();
    	String erreur = "";

    	try {
    		String nom = this.textFieldNom.getText().trim();
    		produit.setNom(nom);
    	} catch (IllegalArgumentException e){
    		erreur = e.getMessage();
    	}
    	try {
    		String description = this.textAreaDescription.getText().trim();
    		produit.setDescription(description);
    	} catch (IllegalArgumentException e){
    		erreur += e.getMessage();
    	}
    	try {
    		if (this.textFieldTarif.getText().trim().length() == 0){
    			erreur += "tarif non renseigné\n";
			}
    		else {
				float tarif = Float.parseFloat(this.textFieldTarif.getText().trim());
				produit.setTarif(tarif);
			}
    	} catch (IllegalArgumentException e){
    		erreur += e.getMessage();
    	}
    	if (categorie == null) {
    		erreur += "Aucune catégorie séléctionnée\n";
    	}
    	else {
    		produit.setCategorie(categorie);
    	}

    	////////TODO
		produit.setVisuel("Default");

		if (erreur.length() == 0) {

			consumer.accept(produit);
			controlMain.pop();
		}
		else {

			MessageBox.show(Alert.AlertType.ERROR, "Erreur", "Champs invalides", erreur);
		}

    }

	public void setCallback(Consumer<Produit> consumer) {

		this.consumer = consumer;
	}

	public void setProduit(Produit oldProduit) {

		textAreaDescription.setText(oldProduit.getDescription());
		choiceBoxCategorie.setValue(oldProduit.getCategorie());
		textFieldTarif.setText(String.valueOf(oldProduit.getTarif()));
		textFieldNom.setText(oldProduit.getNom());

		labelTitre.setText("Modification d'un produit");
	}

	public void setReadOnly(boolean readOnly) {

		textFieldNom.setEditable(!readOnly);
		if(readOnly) choiceBoxCategorie.isDisabled();
		textFieldTarif.setEditable(!readOnly);
		textAreaDescription.setEditable(!readOnly);

		button_Add.setVisible(!readOnly);

		labelTitre.setText("Détails d'un produit");
	}

	public void textField_LimitSize(ObservableValue<? extends String> observable, String oldValue, String newValue, TextField textField) {

		if (newValue.length() > Integer.parseInt((String)textField.getUserData())) {

			textField.setText(oldValue);
		}
	}

	public void button_Annuler_Onclick(MouseEvent mouseEvent) {

    	controlMain.pop();
	}
}
