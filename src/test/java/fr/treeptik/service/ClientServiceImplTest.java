package fr.treeptik.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ClientServiceImplTest {

	@Autowired
	private ClientService clientService;

	@Before
	public void premiereMethode() {
		System.out.println("******* Test Service Client *******");
	}

	@Test
	@Transactional
	public void testSave() {
		try {
			Client client = new Client();
			client.setNom("Nom");
			client.setPrenom("Prenom");
			client.setMail("mail@mail.com");

			clientService.save(client);

		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	@Transactional
	public void testfindall() {
		try {
			Client client = new Client();

			client.setNom("Nom");
			client.setPrenom("Prenom");
			client.setMail("mail@mail.com");
			clientService.save(client);
			client = new Client();
			client.setNom("Nom1");
			client.setPrenom("Prenom1");
			client.setMail("mail1@mail.com");
			clientService.save(client);
			client = new Client();
			client.setNom("Nom2");
			client.setPrenom("Prenom2");
			client.setMail("mail2@mail.com");
			clientService.save(client);
			client = new Client();
			client.setNom("Nom3");
			client.setPrenom("Prenom3");
			client.setMail("mail3@mail.com");
			clientService.save(client);

			List<Client> list = clientService.findAll();
			for (Client client2 : list) {
				System.out.println(client2);
				System.out.println("");

			}

		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
		}
	}

}
