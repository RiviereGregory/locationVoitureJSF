package fr.treeptik.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.ClientDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;

// Attention ne pas l'oubli pour l'injection dans le ClientManagedBean
@Service("clientService")
public class ClientServiceImpl extends GenericServiceImpl<Client, Integer, ClientDAO> implements
		ClientService, Serializable {
	// On utilise serializable car on est en sessionScope dans le clienMB
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientDAO clientDAO;

	@Override
	protected ClientDAO getDao() {
		return clientDAO;
	}

	@Override
	public List<Client> findAllOrderByNomPrenom() throws ServiceException {
		List<Client> list;
		try {
			list = clientDAO.findAllOrderByNomPrenom();
		} catch (DAOException e) {

			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Client> findAllOrderByPrenomNom() throws ServiceException {
		List<Client> list;
		try {
			list = clientDAO.findAllOrderByPrenomNom();
		} catch (DAOException e) {

			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Client> findAllLike(String contient) throws ServiceException {
		List<Client> list;
		try {
			list = clientDAO.findAllLike(contient);
		} catch (DAOException e) {

			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Client findClientByNameAndSurname(String name, String surname) throws ServiceException {
		try {
			return clientDAO.findClientByNameAndSurname(name, surname);
		} catch (DAOException e) {

			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
