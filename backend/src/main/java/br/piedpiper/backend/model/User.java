package br.piedpiper.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TB_USER")
@EqualsAndHashCode(of = "id")
public class User {

	private final static String SEQUENCE_NAME = "SQ_USER";

	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = User.SEQUENCE_NAME, name = User.SEQUENCE_NAME)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = User.SEQUENCE_NAME)
	@Column(name = "CO_SEQ_USER")
	private Long id;

	@Column(name = "DS_USERNAME")
	private String username;

	@Column(name = "DS_EMAIL")
	private String email;
}
