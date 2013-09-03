package fr.treeptik.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;
import fr.treeptik.model.Reservation;
import fr.treeptik.model.Voiture;
import fr.treeptik.service.ReservationService;

@ManagedBean(name = "reservationMB", eager = true)
// On utilise sessonScoped pour utiliser primefaces qui utilise les données en session
@SessionScoped
public class ReservationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injection du service dans les pages à la place du ${} on a #{}
	@ManagedProperty("#{reservationService}")
	private ReservationService reservationService;

	private ListDataModel<Reservation> reservations = new ListDataModel<>();

	private Reservation reservation;

	// Variable pour recupérer les dates des champs des pages xhtml
	private Date dateReservation;
	private Date datePriseVehicule;
	private Date dateRetour;

	// Permet de pouvoir utiliser le set pour initialiser les Objets
	public ReservationManagedBean() {

		reservation = new Reservation();
		reservation.setClient(new Client());
		reservation.setVoiture(new Voiture());
	}

	// Permet d'initialiser la liste qui sera utiliser dans les datatables de primefaces
	public String initListReservation() throws Exception {
		reservations.setWrappedData(reservationService.findAll());
		return "reservations";
	}

	// Les Validators
	public void validateDateReservation(FacesContext context, UIComponent component, Object date)
			throws ValidatorException {
		dateReservation = (Date) date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringCourant = dateFormat.format(new Date());
		Date dateCourante = null;
		try {
			dateCourante = dateFormat.parse(stringCourant);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dateCourante.after(dateReservation) && !dateCourante.equals(dateReservation)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"date reservation vous devez saisir une date future",
					"vous devez saisir une date future"));
		}

	}

	public void validateDatePriseVehicule(FacesContext context, UIComponent component, Object date)
			throws ValidatorException {
		datePriseVehicule = (Date) date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringCourant = dateFormat.format(new Date());
		Date dateCourante = null;
		try {
			dateCourante = dateFormat.parse(stringCourant);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dateCourante.after(datePriseVehicule) && !dateCourante.equals(datePriseVehicule)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"date prise vehicule vous devez saisir une date future",
					"vous devez saisir une date future"));
		}
		if (datePriseVehicule.equals(dateReservation)) {

		} else if (datePriseVehicule.before(dateReservation)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"vous devez saisir une date après la date de réservation",
					"vous devez saisir une date après la date de réservation"));
		}
	}

	public void validateDateRetour(FacesContext context, UIComponent component, Object date)
			throws ValidatorException {
		dateRetour = (Date) date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringCourant = dateFormat.format(new Date());
		Date dateCourante = null;
		try {
			dateCourante = dateFormat.parse(stringCourant);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dateCourante.after(dateRetour) && !dateCourante.equals(dateRetour)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"date retour vous devez saisir une date future",
					"vous devez saisir une date future"));
		}

		if (dateRetour.equals(datePriseVehicule)) {

		} else if (dateRetour.before(datePriseVehicule)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"vous devez saisir une date après la date de prise de vehicule",
					"vous devez saisir une date après la date de prise de vehicule"));
		}
	}

	// Pour modifier une valeur du tableau
	public String modifyReservation() throws Exception {
		// Permet de récupérer l'id du client a modifier
		reservation = reservations.getRowData();
		reservationService.findById(reservation.getId());

		return "reservation";
	}

	// Methode appeler depuis la page avec un bouton plus de notion de GET ou POST
	public String addReservation() throws Exception {

		System.out.println("reservation client id= " + reservation.getClient().getId()
				+ " voiture id = " + reservation.getVoiture().getId());

		reservationService.save(reservation);

		return initListReservation();
	}

	public String deleteReservation() throws Exception {
		// On recupère l'id de l'utisateur que l'on a selectionner dans le tableau il faut utiliser
		// ListDataModel
		reservation = reservations.getRowData();

		reservationService.removeById(reservation.getId());

		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return initListReservation();
	}

	public String reset() {
		this.setReservation(new Reservation());

		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return "reservation";
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
		// Ne pas le mettre pour utiliser primeFaces
		// reservations.setWrappedData(reservationService.findAll());
		return reservations;
	}

	public void setReservations(ListDataModel<Reservation> reservations) {
		this.reservations = reservations;
	}

}