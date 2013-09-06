package fr.treeptik.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.RowEditEvent;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;
import fr.treeptik.model.Reservation;
import fr.treeptik.model.Voiture;
import fr.treeptik.service.ClientService;
import fr.treeptik.service.ReservationService;

@ManagedBean(name = "reservationMB", eager = true)
// On utilise sessonScoped pour utiliser primefaces qui utilise les données en session
@SessionScoped
public class ReservationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injection du service dans les pages à la place du ${} on a #{}
	@ManagedProperty("#{reservationService}")
	private ReservationService reservationService;

	@ManagedProperty("#{clientService}")
	private ClientService clientService;

	private ListDataModel<Reservation> reservations = new ListDataModel<>();

	private Reservation reservation;

	// Variable pour recupérer les dates des champs des pages xhtml
	private Date dateReservation;
	private Date datePriseVehicule;
	private Date dateRetour;
	private Client client;

	// Permet de pouvoir utiliser le set pour initialiser les Objets
	public ReservationManagedBean() {

		reservation = new Reservation();
		reservation.setClient(new Client());
		reservation.setVoiture(new Voiture());
	}

	// Permet d'initialiser la liste qui sera utiliser dans les datatables de primefaces
	public String initListReservation() throws Exception {
		reservations = new ListDataModel<>();
		reservations.setWrappedData(reservationService.findAll());
		return "reservations";
	}

	public void initReservation() {
		reservation = new Reservation();
		reservation.setClient(new Client());
		reservation.setVoiture(new Voiture());
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
			// Internationalisation des messages d'erreur
			ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot()
					.getLocale());

			throw new ValidatorException(new FacesMessage(
					bundle.getString("erreur.reservation.date.reservation")));
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

		// Internationalisation des messages d'erreur
		ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot()
				.getLocale());

		if (dateCourante.after(datePriseVehicule) && !dateCourante.equals(datePriseVehicule)) {
			throw new ValidatorException(new FacesMessage(
					bundle.getString("erreur.reservation.date.prisevoiture")));
		}
		if (datePriseVehicule.equals(dateReservation)) {

		} else if (datePriseVehicule.before(dateReservation)) {
			throw new ValidatorException(new FacesMessage(
					bundle.getString("erreur.reservation.date.prisevoiture.after")));
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
		// Internationalisation des messages d'erreur
		ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot()
				.getLocale());
		if (dateCourante.after(dateRetour) && !dateCourante.equals(dateRetour)) {
			throw new ValidatorException(new FacesMessage(
					bundle.getString("erreur.reservation.date.retour")));
		}

		if (dateRetour.equals(datePriseVehicule)) {

		} else if (dateRetour.before(datePriseVehicule)) {
			throw new ValidatorException(new FacesMessage(
					bundle.getString("erreur.reservation.date.retour.after")));
		}
	}

	public void validateClient(FacesContext context, UIComponent component, Object date)
			throws ValidatorException {

	}

	// Permet d'utiliser les cellules editable
	public void onEdit(RowEditEvent event) throws Exception {
		// Permet de modifier le client dans la base de donnée
		reservationService.save((Reservation) event.getObject());

		// Internationalisation des messages d'erreur
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot()
				.getLocale());
		String key = bundle.getString("bouton.reservation") + " " + bundle.getString("bouton.edit");
		FacesMessage msg = new FacesMessage(key, ((Reservation) event.getObject()).getId()
				.toString());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		// Internationalisation des messages d'erreur
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot()
				.getLocale());
		String key = bundle.getString("bouton.reservation") + " "
				+ bundle.getString("bouton.annule");
		FacesMessage msg = new FacesMessage(key, ((Reservation) event.getObject()).getId()
				.toString());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Permet de selectionner le nom dans un autoComplete primefaces
	public List<Client> completeClient(String query) throws Exception {
		List<Client> items = new ArrayList<>();
		List<Client> allClient = clientService.findAllLike(query);
		for (Client c : allClient) {
			if (c.getNom().startsWith(query)) {
				items.add(c);
			}
		}
		return items;
	}

	public String listReservationByName() throws Exception {
		System.out.println("listreservatio : " + reservation.getClient());
		reservations = new ListDataModel<>();
		System.out.println("listReservationByName : " + reservation.getClient().getId());

		reservations.setWrappedData(reservationService
				.findByClient(reservation.getClient().getId()));
		return "reservations";
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
		// Pour initialiser la reservatoin avec client et voiture
		initReservation();

		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return "reservation";
	}

	public String reservationClient() {
		// Pour initialiser la reservatoin avec client et voiture
		initReservation();
		return "reservationclient";
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

}