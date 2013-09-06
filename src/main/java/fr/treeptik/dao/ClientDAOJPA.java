package fr.treeptik.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Client;

@Repository
public class ClientDAOJPA extends GenericDAOJPA<Client, Integer> implements ClientDAO, Serializable {

	private static final long serialVersionUID = 1L;

	public ClientDAOJPA() {
		super(Client.class);
	}

	@Override
	public List<Client> findAllOrderByNomPrenom() throws DAOException {
		List<Client> list;
		try {
			TypedQuery<Client> createQuery = entityManager.createQuery(
					"SELECT cli FROM Client cli ORDER BY cli.nom ASC, cli.prenom ", Client.class);
			list = createQuery.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public List<Client> findAllOrderByPrenomNom() throws DAOException {
		List<Client> list;
		try {
			TypedQuery<Client> createQuery = entityManager.createQuery(
					"SELECT cli FROM Client cli ORDER BY cli.prenom ASC, cli.nom ", Client.class);
			list = createQuery.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public List<Client> findAllLike(String contient) throws DAOException {
		List<Client> list;
		try {
			TypedQuery<Client> createQuery = entityManager.createQuery(
					"SELECT cli FROM Client cli WHERE cli.nom LIKE :contient ", Client.class);
			createQuery.setParameter("contient", contient + "%");
			list = createQuery.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public Client findClientByNameAndSurname(String name, String surname) throws DAOException {
		Client client;
		try {
			TypedQuery<Client> createQuery = entityManager.createQuery(
					"SELECT cli FROM Client cli WHERE cli.nom = :name AND cli.prenom = :surname ",
					Client.class);
			createQuery.setParameter("name", name);
			createQuery.setParameter("surname", surname);
			client = createQuery.getSingleResult();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return client;
	}

}
