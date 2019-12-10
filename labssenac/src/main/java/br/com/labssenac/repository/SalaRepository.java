package br.com.labssenac.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.labssenac.models.Sala;

public interface SalaRepository extends CrudRepository<Sala, String> {
	
	Sala findById(long id);
	
}
