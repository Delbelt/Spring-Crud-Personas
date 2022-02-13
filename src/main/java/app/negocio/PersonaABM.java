package app.negocio;

import java.util.List;

import org.springframework.stereotype.Service;

import app.datos.Persona;
import app.services.PersonaService;

@Service
public class PersonaABM implements PersonaService {

	@Override
	public List<Persona> traerListaPersona()
	{
		return null;
	}

	@Override
	public Persona traerPersona(Persona persona)
	{
		return null;
	}

	@Override
	public void guardarPersona(Persona persona)
	{
		
	}

	@Override
	public void eliminarPersona(Persona persona)
	{		
	}
}
