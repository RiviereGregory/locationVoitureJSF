package fr.treeptik.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;
import fr.treeptik.service.ClientService;

@ManagedBean(name = "clientMB")
public class ClientManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injection du service dans les pages à la place du ${} on a #{}
	@ManagedProperty("#{clientService}")
	private ClientService clientService;

	private ListDataModel<Client> clients = new ListDataModel<Client>();

	// Permet de pour selectionner les objets dans les setlectitemmenu ou listbox
	private List<SelectItem> selectClient = new ArrayList<>();

	private Client client = new Client();

	// Methode appeler depuis la page avec un bouton plus de notion de GET ou POST
	public String addClient() throws Exception {

		clientService.save(client);

		// Retourne ensuite sur la page list-user
		return "clients";
	}

	public String deleteClient() throws Exception {
		// On recupère l'id de l'utisateur que l'on a selectionner dans le tableau il faut utiliser
		// ListDataModel
		client = clients.getRowData();

		clientService.removeById(client.getId());
		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return "clients";
	}

	public String reset() {
		this.setClient(new Client());
		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return "client";
	}

	public String listClients() {
		return "clients";
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ListDataModel<Client> getClients() throws ServiceException {
		clients.setWrappedData(clientService.findAll());
		return clients;
	}

	public void setClients(ListDataModel<Client> clients) {
		this.clients = clients;
	}

	// Permet de pour selectionner les objets dans les setlectitemmenu ou listbox
	public List<SelectItem> getSelectClient() throws ServiceException {

		List<Client> allClient = clientService.findAll();
		for (Client client : allClient) {

			selectClient.add(new SelectItem(client.getId(), client.getNom() + " - "
					+ client.getPrenom()));
		}

		return selectClient;
	}

	public void setSelectClient(List<SelectItem> selectClient) {
		this.selectClient = selectClient;
	}

}