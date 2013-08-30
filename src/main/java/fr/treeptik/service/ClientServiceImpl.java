package fr.treeptik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.ClientDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Integer, ClientDAO> implements
		ClientService {

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
}
