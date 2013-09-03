package fr.treeptik.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Reservation;

@Repository
public class ReservationDAOJPA extends GenericDAOJPA<Reservation, Integer> implements
		ReservationDAO, Serializable {

	private static final long serialVersionUID = 1L;

	public ReservationDAOJPA() {
		super(Reservation.class);

	}

	@Override
	public List<Reservation> findByClient(Integer id) throws DAOException {
		try {
			TypedQuery<Reservation> query = entityManager.createQuery(
					"SELECT r FROM  Reservation r WHERE r.client.id=:id", Reservation.class);
			query.setParameter("id", id);

			return query.getResultList();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Reservation> findByVoiture(Integer id) throws DAOException {
		try {
			TypedQuery<Reservation> query = entityManager.createQuery(
					"SELECT r FROM  Reservation r WHERE r.voiture.id=:id", Reservation.class);
			query.setParameter("id", id);

			return query.getResultList();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Reservation> findAllOrderByDateReservation() throws DAOException {
		List<Reservation> list;
		try {
			TypedQuery<Reservation> createQuery = entityManager.createQuery(
					"SELECT res FROM Reservation res ORDER BY res.dateReservation ASC",
					Reservation.class);
			list = createQuery.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public List<Reservation> findAllOrderByDatePriseVehicule() throws DAOException {
		List<Reservation> list;
		try {
			TypedQuery<Reservation> createQuery = entityManager.createQuery(
					"SELECT res FROM Reservation res ORDER BY res.datePriseVehicule ASC",
					Reservation.class);
			list = createQuery.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public List<Reservation> findAllOrderByDateRetour() throws DAOException {
		List<Reservation> list;
		try {
			TypedQuery<Reservation> createQuery = entityManager.createQuery(
					"SELECT res FROM Reservation res ORDER BY res.dateRetour ASC",
					Reservation.class);
			list = createQuery.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

}
