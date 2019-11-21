package br.piedpiper.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.piedpiper.backend.model.Lead;
import br.piedpiper.backend.model.LeadStatus;
import br.piedpiper.backend.model.Phone;
import br.piedpiper.backend.model.User;
import br.piedpiper.backend.repository.LeadRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { BackendApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LeadControllerTest {

	@Autowired
	private LeadRepository repository;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		//given
		User responsavel = new User();
		responsavel.setEmail("email@email.com");
		responsavel.setUsername("responsavel");

		Lead firstLead = new Lead();
		firstLead.setAnotacoes("FIRST");
		firstLead.setEmail("first@email.com");
		firstLead.setEmpresa("FirstEmpresa");
		firstLead.setNome("FirstNome");
		firstLead.setSite("FirstSite");
		firstLead.setStatus(LeadStatus.WON);
		firstLead.setResponsavel(responsavel);

		Phone phone = new Phone();
		phone.setNumber("0001");
		phone.setLead(firstLead);

		this.repository.save(firstLead);

		Lead secLead = new Lead();
		secLead.setAnotacoes("FIRST");
		secLead.setEmail("first@email.com");
		secLead.setEmpresa("FirstEmpresa");
		secLead.setNome("FirstNome");
		secLead.setSite("FirstSite");
		secLead.setStatus(LeadStatus.WON);
		secLead.setResponsavel(responsavel);

		phone.setLead(secLead);

		this.repository.save(secLead);

	}

	@Test
	public void listAll() throws Exception {
		this.mockMvc.perform(get("/lead"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}
