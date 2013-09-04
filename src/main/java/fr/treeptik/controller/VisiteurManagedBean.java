package fr.treeptik.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class VisiteurManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String mdp;
	@SuppressWarnings("unused")
	private String nomUtilisateur;

	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.login(login, mdp);
			context.addMessage(null, new FacesMessage("Et hop, reconnu"));
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Unknown login " + e.getMessage()));
		}
	}

	public String logout() throws IOException {
		try {
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest()).logout();

		} catch (ServletException ex) {

		}
		return "accueil";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	// Permet de récuperer le nom utilisateur
	public String getNomUtilisateur() {
		return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}
