package fr.treeptik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.UserDao;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void addUser(User user) throws ServiceException {
		try {
			userDao.saveOrUpdate(user);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			return userDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void updateUser(User user) throws ServiceException {
		// TODO A implementer
		throw new UnsupportedOperationException();
	}

	@Override
	@Transactional
	public void deleteUser(Integer id) throws ServiceException {
		try {
			userDao.remove(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public User getUserById(Integer id) throws ServiceException {
		// TODO A implementer
		throw new UnsupportedOperationException();
	}

}