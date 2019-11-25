package br.piedpiper.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TB_LEAD")
@EqualsAndHashCode(of = "id")
public class Lead {

	private final static String SEQUENCE_NAME = "SQ_LEAD";

	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = Lead.SEQUENCE_NAME, name = Lead.SEQUENCE_NAME)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Lead.SEQUENCE_NAME)
	@Column(name = "CO_SEQ_LEAD")
	private Long id;

	@Column(name = "NO_LEAD")
	private String nome;

	@Column(name = "DS_EMPRESA")
	private String empresa;

	@Column(name = "DS_EMAIL")
	private String email;

	@Column(name = "DS_SITE")
	private String site;

	@OneToMany(mappedBy = "lead", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Phone> telefones = new ArrayList<>();

	@Enumerated
	@Column(name = "TP_STATUS")
	private LeadStatus status = LeadStatus.OPEN;

	@Column(name = "DS_NOTES")
	private String anotacoes;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CO_USER")
	private User responsavel;

}
