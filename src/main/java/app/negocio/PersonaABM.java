package app.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.PersonaDao;
import app.datos.Persona;
import app.services.PersonaService;

@Service
public class PersonaABM implements PersonaService {
	
//	IMPORTANTE EN LA CAPA PONER LAS ANOTACIONES DE "TRANSACCIONES" DEBIDO A QUE SI FALLA
//	ES NECESARIO HACER UN "ROLLBACK" Y EN CASO DE QUE FUNCIONE UN COMMIT, ESTANDO EN LA CAPA DE SERVICIO
//	Y NO LA DAO, ES IMPORTANTE QUE ESTO SEA ASI, PARA RESPETAR LOS PRINCIPIOS ACID.
	
	@Autowired() //INYECCION
	private PersonaDao dao; //CREO EL OBJETO INTERFACE DAO

	@Override
	@Transactional(readOnly = true)	//Como el metodo solamente se va a leer, no afecta a la BD
	public List<Persona> traerListaPersona()
	{
		return (List<Persona>) dao.findAll();
	}

	@Override
	@Transactional //Si no se le aclara nada: va a hacer commit o rollback
	public void guardarPersona(Persona persona)
	{		
		dao.save(persona);		
	}

	@Override
	@Transactional
	public void eliminarPersona(Persona persona)
	{		
		dao.delete(persona);		
	}

	@Override
	@Transactional(readOnly = true)
	public Persona traerPersona(Persona persona)
	{	
		return dao.findById(persona.getIdPersona()).orElse(null);		
	}
}
