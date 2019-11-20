package br.piedpiper.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TB_PHONE")
@EqualsAndHashCode(of = "id")
public class Phone {

	private final static String SEQUENCE_NAME = "SQ_PHONE";

	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = Phone.SEQUENCE_NAME, name = Phone.SEQUENCE_NAME)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Phone.SEQUENCE_NAME)
	@Column(name = "CO_SEQ_PHONE")
	private Long id;

	@Column(name = "DS_NUMBER")
	private String username;

	@ManyToOne
	@JoinColumn(name = "CO_USER")
	private User user;
}
