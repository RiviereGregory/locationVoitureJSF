package fr.treeptik.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;
import fr.treeptik.service.ClientService;

@Component
@FacesConverter("clientConverter")
public class ClientConverter implements Converter {

	private ClientService clientService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("getAsObject converter");
		// String[] params = value.split(" ");
		// System.out.println("Concerter :" + params[0] + "-" + params[1]);
		// Client client = new Client();
		// try {
		// client = clientService.findClientByNameAndSurname(params[0], params[1]);
		// System.out.println("Client " + client);
		// } catch (ServiceException e) {
		// e.printStackTrace();
		// }
		// return client;
		String[] split = value.split("-");
		
		Client client = null;
		try {
			client = clientService.findClientByNameAndSurname(split[0], split[1]);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return client;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("Converter asstring :" + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			
			Client c = (Client) value;
			return c.getNom()+"-"+c.getPrenom();
		}
	}

}
