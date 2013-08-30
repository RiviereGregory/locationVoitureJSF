package fr.treeptik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.VoitureDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Voiture;

@Service("voitureService")
public class VoitureServiceImpl extends GenericServiceImpl<Voiture, Integer, VoitureDAO> implements
		VoitureService {

	@Autowired
	private VoitureDAO voitureDAO;

	@Override
	protected VoitureDAO getDao() {
		return voitureDAO;
	}

	@Override
	public List<Voiture> findAllOrderByMarqueModele() throws ServiceException {
		List<Voiture> list;
		try {
			list = voitureDAO.findAllOrderByMarqueModele();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Voiture> findAllOrderByModeleMarque() throws ServiceException {
		List<Voiture> list;
		try {
			list = voitureDAO.findAllOrderByModeleMarque();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public List<Voiture> findAllOrderByDate() throws ServiceException {
		List<Voiture> list;
		try {
			list = voitureDAO.findAllOrderByDate();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	//
	// @Override
	// @Transactional
	// public Voiture save(Voiture voiture) throws ServiceException {
	// try {
	// voiture = voitureDAO.save(voiture);
	// } catch (DAOException e) {
	// throw new ServiceException(e.getMessage(), e.getCause());
	// }
	// return voiture;
	// }
	//
	// @Override
	// @Transactional
	// public void remove(Voiture voiture) throws ServiceException {
	// try {
	// voitureDAO.remove(voiture);
	// } catch (DAOException e) {
	// throw new ServiceException(e.getMessage(), e.getCause());
	// }
	//
	// }
	//
	// @Override
	// public List<Voiture> findAll() throws ServiceException {
	// List<Voiture> list;
	// try {
	// list = voitureDAO.findAll();
	// } catch (DAOException e) {
	// throw new ServiceException(e.getMessage(), e.getCause());
	// }
	// return list;
	// }
	//
	// @Override
	// public Voiture findVoiture(Integer id) throws ServiceException {
	// Voiture voiture;
	// try {
	// voiture = voitureDAO.findById(id);
	// } catch (DAOException e) {
	// throw new ServiceException(e.getMessage(), e.getCause());
	// }
	// return voiture;
	// }
	//
	// @Override
	// @Transactional
	// public void removeById(Integer id) throws ServiceException {
	// try {
	// voitureDAO.removeById(id);
	// } catch (DAOException e) {
	// throw new ServiceException(e.getMessage(), e.getCause());
	// }
	//
	// }
	//
	//

}
