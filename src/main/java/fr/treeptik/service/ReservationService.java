package fr.treeptik.service;

import java.util.List;

import fr.treeptik.dao.ReservationDAO;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Reservation;

public interface ReservationService extends GenericService<Reservation, Integer, ReservationDAO> {

	List<Reservation> findByClient(Integer id) throws ServiceException;

	List<Reservation> findByVoiture(Integer id) throws ServiceException;

	List<Reservation> findAllOrderByDateReservation() throws ServiceException;

	List<Reservation> findAllOrderByDatePriseVehicule() throws ServiceException;

	List<Reservation> findAllOrderByDateretour() throws ServiceException;

}
