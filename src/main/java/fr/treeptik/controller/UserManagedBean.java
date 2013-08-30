package fr.treeptik.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.ListDataModel;

import fr.treeptik.model.User;
import fr.treeptik.service.UserService;

//si on veut utilise les injections spring on utilise component à la place de ManagedBean qui utilise l'injection jsf
//@Component(value = "userMB")
//@Scope(value = "request")
@ManagedBean(name = "userMB")
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// @Autowired
	// private UserService userService;

	// Injection du service dans les pages à la place du ${} on a #{}
	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	// private List<User> userList;
	private ListDataModel<User> listDataModel;

	private User user = new User();

	// Methode appeler depuis la page avec un bouton plus de notion de GET ou POST
	public String addUser() throws Exception {

		userService.addUser(user);

		// Retourne ensuite sur la page list-user
		return "list-users";
	}

	public void deleteUser() throws Exception {
		// On recupère l'id de l'utisateur que l'on a selectionner dans le tableau il faut utiliser
		// ListDataModel
		user = listDataModel.getRowData();

		userService.deleteUser(user.getId());
	}

	public String listUsers() {
		return "list-users";
	}

	public void reset() {
		this.setUser(new User());
	}

	public ListDataModel<User> getUserList() throws Exception {
		listDataModel = new ListDataModel<>(userService.getAllUsers());

		return listDataModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}