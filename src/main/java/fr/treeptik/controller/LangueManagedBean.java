package fr.treeptik.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "lMB")
@SessionScoped
public class LangueManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Pour pouvoir naviguer avec la locale
	private Locale locale = Locale.FRENCH;

	public String activerFR() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.FRENCH);
		this.locale = Locale.FRENCH;
		return null;
	}

	public String activerEN() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.ENGLISH);
		this.locale = Locale.ENGLISH;
		return null;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
