package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.ClientDAO;
import metier.Client;

public class ListeMemoireClientDAO implements ClientDAO {

	private static ListeMemoireClientDAO instance;

	private List<Client> clients;


	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}

		return instance;
	}

	private ListeMemoireClientDAO() {

		this.clients = new ArrayList<Client>();

		this.clients.add(new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays"));
		this.clients.add(new Client(2, "nom2", "prenom2", "identifiant2", "mdp2", "num2", "voie2", "cp2", "ville2", "pays2"));
	}


	@Override
	public boolean create(Client client) {

		client.setIdClient(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.clients.contains(client)) {

			client.setIdClient(client.getIdClient() + 1);
		}
		return this.clients.add(client);
	}

	@Override
	public boolean update(Client client) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.clients.indexOf(client);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une Client inexistante");
		} else {

			this.clients.set(idx, client);
		}

		return true;
	}

	@Override
	public boolean delete(Client client) {

		Client supprime;

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.clients.indexOf(client);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une Client inexistante");
		} else {
			supprime = this.clients.remove(idx);
		}

		return client.equals(supprime);
	}

	@Override
	public Client getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.clients.indexOf(new Client(id, "nom3", "prenom3", "identifiant3", "mdp3", "num3", "voie3", "cp3", "ville3", "pays3"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune Client ne possède cet identifiant");
		} else {
			return this.clients.get(idx);
		}
	}

	@Override
	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.clients;
	}





	

	@Override
	public ArrayList<Client> getByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByPrenom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByIdentifiant(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByMpd(String mdp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByNum(String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByVoie(String voie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByCode(String cp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByVille(String ville) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getByPays(String pays) {
		// TODO Auto-generated method stub
		return null;
	}
}

