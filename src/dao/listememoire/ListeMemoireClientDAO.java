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

		client.setIdClient(1);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.clients.contains(client)) {

			client.setIdClient(client.getIdClient() + 1);
		}
		return this.clients.add(client);
	}

	@Override
	public boolean update(Client client) {

		// Ne fonctionne que si l'objet métier est bien fait...
		try {
			this.clients.set(client.getIdClient(), client);
			return true;
		} catch(Exception e) {
			return false;
		}
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

		ArrayList<Client> clients = new ArrayList<>(this.clients);
		return clients;
	}

	@Override
	public ArrayList<Client> getByNom(String nom) {
		ArrayList<Client> lesNoms=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getNom().equals(nom)) {
				lesNoms.add(client);
			}
		}
		return lesNoms;
	}

	@Override
	public ArrayList<Client> getByPrenom(String prenom) {
		ArrayList<Client> lesPrenoms=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getPrenom().equals(prenom)) {
				lesPrenoms.add(client);
			}
		}
		return lesPrenoms;
	}

	@Override
	public ArrayList<Client> getByIdentifiant(String identifiant) {
		ArrayList<Client> lesIds=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getIdentifiant().equals(identifiant)) {
				lesIds.add(client);
			}
		}
		return lesIds;
	}

	@Override
	public ArrayList<Client> getByMpd(String mdp) {
		ArrayList<Client> lesMdp=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getMdp().equals(mdp)) {
				lesMdp.add(client);
			}
		}
		return lesMdp;
	}

	@Override
	public ArrayList<Client> getByNum(String num) {
		ArrayList<Client> lesNums=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getNum().equals(num)) {
				lesNums.add(client);
			}
		}
		return lesNums;
	}

	@Override
	public ArrayList<Client> getByVoie(String voie) {
		ArrayList<Client> lesVoies=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getVoie().equals(voie)) {
				lesVoies.add(client);
			}
		}
		return lesVoies;
	}

	@Override
	public ArrayList<Client> getByCode(String cp) {
		ArrayList<Client> lesCodes=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getCp().equals(cp)) {
				lesCodes.add(client);
			}
		}
		return lesCodes;
	}

	@Override
	public ArrayList<Client> getByVille(String ville) {
		ArrayList<Client> lesVilles=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getVille().equals(ville)) {
				lesVilles.add(client);
			}
		}
		return lesVilles;
	}

	@Override
	public ArrayList<Client> getByPays(String pays) {
		ArrayList<Client> lesPays=new ArrayList<Client>();
		for(Client client : clients)
		{
			if(client.getPays().equals(pays)) {
				lesPays.add(client);
			}
		}
		return lesPays;
	}
}

