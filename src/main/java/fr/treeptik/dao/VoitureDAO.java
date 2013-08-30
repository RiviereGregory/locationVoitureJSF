package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Voiture;

public interface VoitureDAO extends GenericDAO<Voiture, Integer> {

	List<Voiture> findAllOrderByMarqueModele() throws DAOException;

	List<Voiture> findAllOrderByModeleMarque() throws DAOException;

	List<Voiture> findAllOrderByDate() throws DAOException;

	// List<Voiture> findIsDispo(Date debut, Date fin) throws DAOException;

	// Voiture save(Voiture voiture) throws DAOException;
	//
	// void remove(Voiture voiture) throws DAOException;
	//
	// List<Voiture> findAll() throws DAOException;
	//
	// Voiture findVoiture(Integer id) throws DAOException;
	//
	// void removeById(Integer id) throws DAOException;
}
