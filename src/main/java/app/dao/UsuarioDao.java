package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.datos.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);
	//Es necesario agregar el metodo, lo pide Spring-segurity
}
