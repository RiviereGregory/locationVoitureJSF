package fr.treeptik.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "lMB")
public class LangueManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String activerFR() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.FRENCH);
		return null;
	}

	public String activerEN() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.ENGLISH);
		return null;
	}

}
