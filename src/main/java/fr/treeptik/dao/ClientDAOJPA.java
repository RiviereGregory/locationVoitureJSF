package fr.treeptik.dao;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Client;

@Repository
public class ClientDAOJPA extends GenericDAOJPA<Client, Integer> implements ClientDAO {

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

}
