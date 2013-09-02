package fr.treeptik.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Voiture;
import fr.treeptik.service.VoitureService;

@ManagedBean(name = "voitureMB", eager = true)
public class VoitureManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injection du service dans les pages à la place du ${} on a #{}
	@ManagedProperty("#{voitureService}")
	private VoitureService voitureService;

	private ListDataModel<Voiture> voitures = new ListDataModel<>();

	// Permet de pour selectionner les objets dans les setlectitemmenu ou listbox
	private List<SelectItem> selectVoiture = new ArrayList<>();

	private Voiture voiture = new Voiture();

	// Methode appeler depuis la page avec un bouton plus de notion de GET ou POST
	public String addVoiture() throws Exception {

		voitureService.save(voiture);

		// Retourne ensuite sur la page list-user
		return "voitures";
	}

	public String deleteVoiture() throws Exception {
		// On recupère l'id de l'utisateur que l'on a selectionner dans le tableau il faut utiliser
		// ListDataModel
		voiture = voitures.getRowData();

		voitureService.removeById(voiture.getId());

		// On met un return pour ne pas avoir d'erreur dans la page xhtml car action attent un
		// string pas un void
		return "voitures";
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
		voitures.setWrappedData(voitureService.findAll());
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