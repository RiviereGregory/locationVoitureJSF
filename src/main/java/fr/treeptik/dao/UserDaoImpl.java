package fr.treeptik.dao;

import org.springframework.stereotype.Repository;

import fr.treeptik.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

}