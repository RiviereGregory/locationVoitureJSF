package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Reservation;

public interface ReservationDAO extends GenericDAO<Reservation, Integer> {

	List<Reservation> findByClient(Integer id) throws DAOException;

	List<Reservation> findByVoiture(Integer id) throws DAOException;

	List<Reservation> findAllOrderByDateReservation() throws DAOException;

	List<Reservation> findAllOrderByDatePriseVehicule() throws DAOException;

	List<Reservation> findAllOrderByDateRetour() throws DAOException;

}
