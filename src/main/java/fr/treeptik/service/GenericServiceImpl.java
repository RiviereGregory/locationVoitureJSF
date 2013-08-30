package fr.treeptik.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.GenericDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

public abstract class GenericServiceImpl<T, PK, D extends GenericDAO<T, PK>> implements
		GenericService<T, PK, D> {

	protected abstract D getDao();

	@Override
	@Transactional
	public T save(T entite) throws ServiceException {
		try {
			entite = getDao().save(entite);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return entite;
	}

	@Override
	@Transactional
	public void remove(T entite) throws ServiceException {
		try {

			getDao().remove(entite);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public T findById(PK id) throws ServiceException {
		try {
			return getDao().findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<T> findAll() throws ServiceException {
		List<T> list;
		try {
			list = getDao().findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	@Transactional
	public void removeById(PK id) throws ServiceException {
		try {

			getDao().removeById(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

}
