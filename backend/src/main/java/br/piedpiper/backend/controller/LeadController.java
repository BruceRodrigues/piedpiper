package br.piedpiper.backend.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.piedpiper.backend.model.Lead;
import br.piedpiper.backend.repository.LeadRepository;

@RestController
@RequestMapping(path = "/lead")
@AllArgsConstructor
public class LeadController {

	private LeadRepository repository;

	@GetMapping
	public ResponseEntity<Iterable<Lead>> list() {
		return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
	}
}
