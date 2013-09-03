package fr.treeptik.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Voiture;
import fr.treeptik.service.VoitureService;

@ManagedBean(name = "voitureMB", eager = true)
// Pour utiliser Primefaces
@SessionScoped
public class VoitureManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injection du service dans les pages à la place du ${} on a #{}
	@ManagedProperty("#{voitureService}")
	private VoitureService voitureService;

	private ListDataModel<Voiture> voitures = new ListDataModel<>();

	// Permet de pour selectionner les objets dans les setlectitemmenu ou listbox
	private List<SelectItem> selectVoiture = new ArrayList<>();

	private Voiture voiture = new Voiture();

	private Date dateMiseEnCirculation;

	// Les Validators
	public void validateDateMiseEnCirculation(FacesContext context, UIComponent component,
			Object date) throws ValidatorException {
		dateMiseEnCirculation = (Date) date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringCourant = dateFormat.format(new Date());
		Date dateCourante = null;
		try {
			dateCourante = dateFormat.parse(stringCourant);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dateCourante.before(dateMiseEnCirculation)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"date de mise en circulation vous devez saisir une date passé",
					"vous devez saisir une date passé"));
		}

	}

	// Permet d'initialiser la liste qui sera utiliser dans les datatables de primefaces
	public String initListVoiture() throws Exception {
		voitures.setWrappedData(voitureService.findAll());
		return "voitures";
	}

	// Pour modifier une valeur du tableau
	public String modifyVoiture() throws Exception {
		// Permet de récupérer l'id du client a modifier
		voiture = voitures.getRowData();
		voitureService.findById(voiture.getId());

		return "voiture";
	}

	// Methode appeler depuis la page avec un bouton plus de notion de GET ou POST
	public String addVoiture() throws Exception {

		voitureService.save(voiture);

		// Retourne ensuite sur la page list-user
		return initListVoiture();
	}

	public String deleteVoiture() throws Exception {
		// On recupère l'id de l'utisateur que l'on a selectionner dans le tableau il faut utiliser
		// ListDataModel
		voiture = voitures.getRowData();

		voitureService.removeById(voiture.getId());

		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return initListVoiture();
	}

	public String reset() {
		this.setVoiture(new Voiture());

		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return "voiture";
	}

	public String listVoitures() {
		return "voitures";
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public VoitureService getVoitureService() {
		return voitureService;
	}

	public void setVoitureService(VoitureService voitureService) {
		this.voitureService = voitureService;
	}

	public ListDataModel<Voiture> getVoitures() throws ServiceException {
		// Pour utiliser le trie dans primefaces
		// voitures.setWrappedData(voitureService.findAll());
		return voitures;
	}

	public void setVoitures(ListDataModel<Voiture> voitures) {
		this.voitures = voitures;
	}

	// Permet de pour selectionner les objets dans les setlectitemmenu ou listbox
	public List<SelectItem> getSelectVoiture() throws ServiceException {
		List<Voiture> allVoiture = voitureService.findAll();
		for (Voiture voiture : allVoiture) {

			selectVoiture.add(new SelectItem(voiture.getId(), voiture.getMarque() + " - "
					+ voiture.getModele()));

		}

		return selectVoiture;
	}

	public void setSelectVoiture(List<SelectItem> selectVoiture) {
		this.selectVoiture = selectVoiture;
	}

}