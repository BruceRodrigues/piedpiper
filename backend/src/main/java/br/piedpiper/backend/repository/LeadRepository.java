package br.piedpiper.backend.repository;

import org.springframework.data.repository.CrudRepository;

import br.piedpiper.backend.model.Lead;

public interface LeadRepository extends CrudRepository<Lead, Long> {
}
