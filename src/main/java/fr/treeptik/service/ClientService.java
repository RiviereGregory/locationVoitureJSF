package fr.treeptik.service;

import java.util.List;

import fr.treeptik.dao.ClientDAO;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;

public interface ClientService extends GenericService<Client, Integer, ClientDAO> {

	List<Client> findAllOrderByNomPrenom() throws ServiceException;

	List<Client> findAllOrderByPrenomNom() throws ServiceException;

	List<Client> findAllLike(String contient) throws ServiceException;

	Client findClientByNameAndSurname(String name, String surname) throws ServiceException;

}
