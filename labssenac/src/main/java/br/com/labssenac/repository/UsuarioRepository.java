package br.com.labssenac.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.labssenac.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	Usuario findById(long id);
	
}
