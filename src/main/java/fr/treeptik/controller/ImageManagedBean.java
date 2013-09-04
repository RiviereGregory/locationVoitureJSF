package fr.treeptik.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ImageManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> images;

	public ImageManagedBean() {
		images = new ArrayList<String>();
		images.add("voiture.jpg");
		images.add("voiture1.jpg");
		images.add("voiture2.gif");
	}

	public List<String> getImages() {
		return images;
	}

}
