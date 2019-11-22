package br.piedpiper.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import br.piedpiper.backend.model.Lead;

@Transactional
public interface LeadRepository extends CrudRepository<Lead, Long> {

	List<Lead> findByNomeContaining(String nome);

}
