package app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.negocio.PersonaABM;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorDAO {
	
	@Autowired(required=true) //Inyeccion
	private PersonaABM sistema; //Instancia el objeto de Negocio para aplicar los metodos
	
	@GetMapping("/")
	public String inicio(Model model)
	{			
		var listaPersona = sistema.traerListaPersona(); //Variable de Lombook
		//List<Persona> listaPersona = new ArrayList<Persona>(); //Similar a esto, pero no hace falta definir que "tipo" es
		
		log.info("Inicio Controlador DAO");
		
		model.addAttribute("listaPersona", listaPersona); //Agrega el atributo a la pantalla de inicio
		
		return "inicio";
	}

}
