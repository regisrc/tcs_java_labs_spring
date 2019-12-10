package br.com.labssenac.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.labssenac.models.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, String> {
	
	Reserva findById(long id);
	
}
