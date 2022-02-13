package app.services;

import java.util.List;

import app.datos.Persona;

public interface PersonaService {
//Capa de servicio, con los metodos a desarrollar en el ABM
	
	public List<Persona> traerListaPersona();
	
	public Persona traerPersona(Persona persona);
	
	public void guardarPersona(Persona persona);
	
	public void eliminarPersona(Persona persona);
}
