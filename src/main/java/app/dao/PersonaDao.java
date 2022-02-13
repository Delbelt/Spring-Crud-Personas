package app.dao;

import org.springframework.data.repository.CrudRepository;

import app.datos.Persona;

public interface PersonaDao extends CrudRepository <Persona, Long> {
	//Hereda los metodos CRUD para manipular la BD a traves de Spring
	//Se puede agregar Querys personalizables.
}
