package br.com.labssenac.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.labssenac.models.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, String> {
	
	Professor findById(long id);
	
}
