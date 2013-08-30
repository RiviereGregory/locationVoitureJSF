package fr.treeptik.dao;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Voiture;

@Repository
public class VoitureDAOJPA extends GenericDAOJPA<Voiture, Integer> implements VoitureDAO {

	public VoitureDAOJPA() {
		super(Voiture.class);
	}

	@Override
	public List<Voiture> findAllOrderByMarqueModele() throws DAOException {
		List<Voiture> list;
		try {
			TypedQuery<Voiture> query = entityManager.createQuery(
					"SELECT voi FROM Voiture voi ORDER BY voi.marque ASC, voi.modele",
					Voiture.class);

			list = query.getResultList();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public List<Voiture> findAllOrderByModeleMarque() throws DAOException {
		List<Voiture> list;
		try {
			TypedQuery<Voiture> query = entityManager.createQuery(
					"SELECT voi FROM Voiture voi ORDER BY voi.modele ASC, voi.marque",
					Voiture.class);

			list = query.getResultList();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public List<Voiture> findAllOrderByDate() throws DAOException {
		List<Voiture> list;
		try {
			TypedQuery<Voiture> query = entityManager.createQuery(
					"SELECT voi FROM Voiture voi ORDER BY voi.dateMiseEnCirculation ASC",
					Voiture.class);

			list = query.getResultList();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	// @Override
	// public List<Voiture> findIsDispo(Date debut, Date fin) throws DAOException {
	// List<Voiture> list;
	// try {
	// TypedQuery<Voiture> query = entityManager
	// .createQuery(
	// "SELECT voi FROM VOITURE JOIN voi.reservations res WHERE voi.reservations.datePriseVehicule = :debut",
	// Voiture.class);
	// query.setParameter("debut", debut);
	//
	// list = query.getResultList();
	//
	// } catch (PersistenceException e) {
	// throw new DAOException(e.getMessage(), e.getCause());
	// }
	//
	// return list;
	// }

	// @PersistenceContext
	// private EntityManager entityManager;
	//
	// @Override
	// public Voiture save(Voiture voiture) throws DAOException {
	// try {
	// // Utilisation de merge au lieu de persist pour faire un update
	// // Attention il faut l'id pour faire un update aussinon il fait un insert
	// entityManager.merge(voiture);
	// } catch (PersistenceException e) {
	// throw new DAOException(e.getMessage(), e.getCause());
	// }
	//
	// return voiture;
	// }
	//
	// @Override
	// public void remove(Voiture voiture) throws DAOException {
	// try {
	// entityManager.remove(voiture);
	// } catch (PersistenceException e) {
	// throw new DAOException(e.getMessage(), e.getCause());
	// }
	//
	// }
	//
	// @Override
	// public List<Voiture> findAll() throws DAOException {
	// try {
	// return entityManager.createQuery("SELECT v FROM Voiture v", Voiture.class)
	// .getResultList();
	// } catch (PersistenceException e) {
	// throw new DAOException(e.getMessage(), e.getCause());
	// }
	// }
	//
	// @Override
	// public Voiture findVoiture(Integer id) throws DAOException {
	// try {
	// return entityManager.find(Voiture.class, id);
	// } catch (PersistenceException e) {
	// throw new DAOException(e.getMessage(), e.getCause());
	// }
	// }
	//
	// @Override
	// public void removeById(Integer id) throws DAOException {
	// try {
	// Query query = entityManager.createQuery("DELETE FROM Voiture v WHERE v.id = :id");
	// query.setParameter("id", id);
	// query.executeUpdate();
	// } catch (PersistenceException e) {
	// throw new DAOException(e.getMessage(), e.getCause());
	// }
	// }

}
