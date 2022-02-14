package app.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import app.datos.Persona;
import app.negocio.PersonaABM;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorDAO {
	
	@Autowired(required=true) //Inyeccion
	private PersonaABM sistema; //Instancia el objeto de Negocio para aplicar los metodos
	
	@GetMapping("/")
	public String inicio(Model model, @AuthenticationPrincipal User user)
	{			
		var listaPersona = sistema.traerListaPersona(); //Variable de Lombook
		//List<Persona> listaPersona = new ArrayList<Persona>(); //Similar a esto, pero no hace falta definir que "tipo" es
		log.info("USER LOGIN: " + user.getUsername());
		log.info("Inicio Controlador DAO");
		
		model.addAttribute("listaPersona", listaPersona); //Agrega el atributo a la pantalla de inicio
		
		return "inicio";
	}
	
	@GetMapping("/agregarPersona")
	public String agregarPersona(Persona persona)
	{
		return "modificarPersona";
	}
	
	@PostMapping("/guardar") //Se utilizan para enviar información al servidor y para procesar solicitudes de publicación
	public String guardar(@Valid Persona persona, Errors error)
	//Inyeccion automatica, al ser un metodo POST busca la referencia en th:action="@{/guardar}" method="post"
	{		
		if(error.hasErrors()) //En caso de un error en las validaciones
		{
			return "modificarPersona"; //Se queda en la pagina y muestra los errores
		}
		
		sistema.guardarPersona(persona);
		
		return "redirect:/";
	}
	
	//Metodo: PATH VARIABLE - Para pasarle el dato al metodo
	@GetMapping("/editar/{idPersona}") //Al pasarle el ID: Spring lo relaciona con el parametro "Persona"
	public String editarPersona(Persona persona, Model model)
	{		
		persona = sistema.traerPersona(persona);
		
		model.addAttribute("persona", persona);
		
		return "modificarPersona";
	}
	
	//Metodo: QUERY PARAMETERS - Para pasarle el dato al metodo
	@GetMapping("/eliminar") //En este caso se relaciona el ID en el componente HTML por ende no es necesario agregar a la ruta
	public String eliminarPersona(Persona persona) //El controlador lo relaciona en el HTML para pasarlo al metodo
	{		
		sistema.eliminarPersona(persona);
		
		return "redirect:/";
	}
	
	@GetMapping("/traer/{idPersona}") //Path Variable
	public String buscarPersonaId(Persona persona, Model model) //Relaciona el Id con el parametro Persona
	{
		persona = sistema.traerPersona(persona); //Trae la persona con el Id
		model.addAttribute("persona", persona);
		
		return "traerPersona";		
	}
}