package fr.treeptik.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Reservation;
import fr.treeptik.service.ReservationService;

@ManagedBean(name = "reservationMB", eager = true)
public class ReservationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injection du service dans les pages à la place du ${} on a #{}
	@ManagedProperty("#{reservationService}")
	private ReservationService reservationService;

	private ListDataModel<Reservation> reservations = new ListDataModel<>();

	private Reservation reservation = new Reservation();

	// Methode appeler depuis la page avec un bouton plus de notion de GET ou POST
	public String addReservation() throws Exception {

		reservationService.save(reservation);

		// Retourne ensuite sur la page list-user
		return "reservations";
	}

	public void deleteReservation() throws Exception {
		// On recupère l'id de l'utisateur que l'on a selectionner dans le tableau il faut utiliser
		// ListDataModel
		reservation = reservations.getRowData();

		reservationService.removeById(reservation.getId());
	}

	public void reset() {
		this.setReservation(new Reservation());
	}

	public String listReservations() {
		return "reservations";
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public ReservationService getResrvationService() {
		return reservationService;
	}

	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	public ListDataModel<Reservation> getReservations() throws ServiceException {
		reservations.setWrappedData(reservationService.findAll());
		return reservations;
	}

	public void setReservations(ListDataModel<Reservation> reservations) {
		this.reservations = reservations;
	}

}