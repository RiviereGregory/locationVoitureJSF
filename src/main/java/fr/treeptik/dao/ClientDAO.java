package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Client;

public interface ClientDAO extends GenericDAO<Client, Integer> {

	List<Client> findAllOrderByNomPrenom() throws DAOException;

	List<Client> findAllOrderByPrenomNom() throws DAOException;

}
