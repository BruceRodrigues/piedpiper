package br.piedpiper.backend.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.piedpiper.backend.model.Lead;
import br.piedpiper.backend.repository.LeadRepository;

@RestController
@RequestMapping(path = "/lead")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class LeadController {

	private LeadRepository repository;

	@GetMapping
	public ResponseEntity<Iterable<Lead>> list(@RequestParam(required = false) String name) {
		Iterable<Lead> leads = StringUtils.isEmpty(name) ? this.repository.findAll() : this.repository.findByNomeContaining(name);
		return new ResponseEntity<>(leads, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Lead> create(@RequestBody Lead lead) {
		Lead saved = this.repository.save(lead);
		return new ResponseEntity<>(saved, HttpStatus.OK);

	}
}
