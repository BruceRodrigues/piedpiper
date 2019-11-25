package br.piedpiper.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
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
public class LeadIntegrationTest {

	@Autowired
	private LeadRepository repository;

	@Autowired
	private MockMvc mockMvc;

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
		firstLead.setTelefones(Arrays.asList(phone));

		this.repository.save(firstLead);

		User responsavelSec = new User();
		responsavelSec.setEmail("emailSec@email.com");
		responsavelSec.setUsername("responsavelSec");

		Lead secLead = new Lead();
		secLead.setAnotacoes("SEC");
		secLead.setEmail("sec@email.com");
		secLead.setEmpresa("SecEmpresa");
		secLead.setNome("SecNome");
		secLead.setSite("SecSite");
		secLead.setStatus(LeadStatus.LOST);
		secLead.setResponsavel(responsavelSec);

		phone.setLead(secLead);

		this.repository.save(secLead);
	}

	@Test
	@Rollback
	public void listAll() throws Exception {
		//given
		this.setup();

		//when
		this.mockMvc.perform(get("/lead"))

				//then
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		//				.andExpect(jsonPath("$", hasSize(2)))
		//				.andExpect(jsonPath("$[0].email", is("first@email.com")))
		//				.andExpect(jsonPath("$[0].anotacoes", is("FIRST")))
		//				.andExpect(jsonPath("$[0].empresa", is("FirstEmpresa")))
		//				.andExpect(jsonPath("$[0].nome", is("FirstNome")))
		//				.andExpect(jsonPath("$[0].site", is("FirstSite")))
		//				.andExpect(jsonPath("$[0].status", is("WON")))
		//				.andExpect(jsonPath("$[0].responsavel.email", is("email@email.com")))
		//				.andExpect(jsonPath("$[0].responsavel.username", is("responsavel")))
		//				.andExpect(jsonPath("$[0].telefones[0].number", is("0001")))
		//
		//				.andExpect(jsonPath("$[1].email", is("sec@email.com")))
		//				.andExpect(jsonPath("$[1].anotacoes", is("SEC")))
		//				.andExpect(jsonPath("$[1].empresa", is("SecEmpresa")))
		//				.andExpect(jsonPath("$[1].nome", is("SecNome")))
		//				.andExpect(jsonPath("$[1].site", is("SecSite")))
		//				.andExpect(jsonPath("$[1].status", is("LOST")))
		//				.andExpect(jsonPath("$[1].responsavel.email", is("emailSec@email.com")))
		//				.andExpect(jsonPath("$[1].responsavel.username", is("responsavelSec")))
		//				.andExpect(jsonPath("$[1].telefones").isEmpty())
		;
	}

	@Test
	@Rollback
	public void findFirst() throws Exception {
		//given

		//FIXME transação não esta rolling back
		//		this.setup();

		//when
		this.mockMvc.perform(get("/lead").param("name", "First"))

				//then
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].email", is("first@email.com")))
				.andExpect(jsonPath("$[0].anotacoes", is("FIRST")))
				.andExpect(jsonPath("$[0].empresa", is("FirstEmpresa")))
				.andExpect(jsonPath("$[0].nome", is("FirstNome")))
				.andExpect(jsonPath("$[0].site", is("FirstSite")))
				.andExpect(jsonPath("$[0].status", is("WON")))
				.andExpect(jsonPath("$[0].responsavel.email", is("email@email.com")))
				.andExpect(jsonPath("$[0].responsavel.username", is("responsavel")))
				.andExpect(jsonPath("$[0].telefones[0].number", is("0001")))
		;
	}

	@Test
	@Rollback
	public void findNone() throws Exception {
		//given

		//FIXME transação não esta rolling back
		//		this.setup();

		//when
		this.mockMvc.perform(get("/lead").param("name", "ASD"))

				//then
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(0)))
		;
	}

	@Test
	public void save() throws Exception {
		this.mockMvc.perform(post("/lead")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"nome\":\"Carlos\","
						+ "\"empresa\":\"BR\","
						+ "\"site\":\"www.google.com\","
						+ "\"email\":\"first@email.com\","
						+ "\"anotacoes\":\"OK\""
						+ "}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is("first@email.com")))
				.andExpect(jsonPath("$.anotacoes", is("OK")))
				.andExpect(jsonPath("$.empresa", is("BR")))
				.andExpect(jsonPath("$.nome", is("Carlos")))
				.andExpect(jsonPath("$.site", is("www.google.com")))
				.andExpect(jsonPath("$.status", is("OPEN")));

		List<Lead> leads = this.repository.findByNomeContaining("Carlos");

		assertThat(leads.size()).isEqualTo(1);
		assertThat(leads.get(0).getNome()).isEqualTo("Carlos");
		assertThat(leads.get(0).getAnotacoes()).isEqualTo("OK");
		assertThat(leads.get(0).getEmail()).isEqualTo("first@email.com");
		assertThat(leads.get(0).getEmpresa()).isEqualTo("BR");
		assertThat(leads.get(0).getSite()).isEqualTo("www.google.com");
		assertThat(leads.get(0).getStatus()).isEqualTo(LeadStatus.OPEN);
	}
}
