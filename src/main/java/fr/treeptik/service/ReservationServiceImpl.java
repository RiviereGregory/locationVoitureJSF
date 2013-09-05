package fr.treeptik.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.ReservationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Reservation;

@Service("reservationService")
public class ReservationServiceImpl extends
		GenericServiceImpl<Reservation, Integer, ReservationDAO> implements ReservationService,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ReservationDAO reservationDAO;

	@Override
	protected ReservationDAO getDao() {

		return reservationDAO;
	}

	@Override
	public List<Reservation> findByClient(Integer id) throws ServiceException {
		List<Reservation> list;
		try {
			list = getDao().findByClient(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Reservation> findByClientName(String nom) throws ServiceException {
		List<Reservation> list;
		try {
			list = getDao().findByClientName(nom);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Reservation> findByVoiture(Integer id) throws ServiceException {
		List<Reservation> list;
		try {
			list = getDao().findByVoiture(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Reservation> findAllOrderByDateReservation() throws ServiceException {
		List<Reservation> list;
		try {
			list = getDao().findAllOrderByDateReservation();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Reservation> findAllOrderByDatePriseVehicule() throws ServiceException {
		List<Reservation> list;
		try {
			list = getDao().findAllOrderByDatePriseVehicule();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Reservation> findAllOrderByDateretour() throws ServiceException {
		List<Reservation> list;
		try {
			list = getDao().findAllOrderByDateRetour();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

}
