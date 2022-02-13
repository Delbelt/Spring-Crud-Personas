package app.datos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data //Agrega el Boilerplate de manera automatica
@Entity //Declara como Entidad general(Puede ser tambien de Hibernate)
@Table(name = "persona") //No es necesario, pero es buena practica ya que puede fallar el case sensitive entre la BD y la aplicacion
public class Persona implements Serializable { //Habilita la Serializacion de la clase

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPersona;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotNull
	private Long dni; //Si se pone en minuscula {primitiva} no funciona ningun tipo numerico en los componentes HTML
}
