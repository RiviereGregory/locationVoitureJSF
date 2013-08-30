package fr.treeptik.service;

import java.util.List;

import fr.treeptik.dao.VoitureDAO;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Voiture;

public interface VoitureService extends GenericService<Voiture, Integer, VoitureDAO> {

	List<Voiture> findAllOrderByMarqueModele() throws ServiceException;

	List<Voiture> findAllOrderByModeleMarque() throws ServiceException;

	List<Voiture> findAllOrderByDate() throws ServiceException;

	// Voiture save(Voiture voiture) throws ServiceException;
	//
	// void remove(Voiture voiture) throws ServiceException;
	//
	// List<Voiture> findAll() throws ServiceException;
	//
	// Voiture findVoiture(Integer id) throws ServiceException;
	//
	// void removeById(Integer id) throws ServiceException;
}
