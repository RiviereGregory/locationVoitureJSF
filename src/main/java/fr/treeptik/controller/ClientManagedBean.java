package fr.treeptik.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.ListDataModel;

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

	private Client client = new Client();

	// Methode appeler depuis la page avec un bouton plus de notion de GET ou POST
	public String addClient() throws Exception {

		clientService.save(client);

		// Retourne ensuite sur la page list-user
		return "clients";
	}

	public void deleteClient() throws Exception {
		// On recupère l'id de l'utisateur que l'on a selectionner dans le tableau il faut utiliser
		// ListDataModel
		client = clients.getRowData();

		clientService.removeById(client.getId());
	}

	public void reset() {
		this.setClient(new Client());
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

}