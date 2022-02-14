package app.segurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //Clase de configuracion Spring
@EnableWebSecurity //Habilita la seguridad Web
public class Security extends WebSecurityConfigurerAdapter {
	
	//WebSecurityConfigurerAdapter
		//Para configurar los usuarios que vamos a utilizar
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception //Crea los usuarios de Autentificacion (Concepto)
		{
			auth
			.inMemoryAuthentication()
			.withUser("admin") //Creo el usuario: admin
			.password("{noop}123") //Almacenar la contraseña como texto sin formato
			.roles("ADMIN", "USER") //Los roles que tiene asignado - Administrador - Usuario
			.and()
			.withUser("user") //Creo el usuario: User
			.password("{noop}123")
			.roles("USER"); //Rol SOLO de usuario
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception //Configura las Autorizaciones (Concepto) a partir de los roles
		{
			http
			.authorizeHttpRequests()
			.antMatchers("/editar/**", "/agregarPersona/**", "/eliminar") // /** Es para TODA ruta siguiente a esa
			.hasRole("ADMIN") // ↑↑↑ Quien puede acceder a estas rutas
			.antMatchers("/","/traer/**") //Ingreso al inicio (publico)
			.hasAnyRole("USER", "ADMIN")//Cualquier Usuario
			.and()
			.formLogin() //Agrega el formulario de Login
			.loginPage("/login") //Ruta Login ("/")
			.and()
			.exceptionHandling().accessDeniedPage("/errores/403"); //Acceso Denegado - Ruta("/")
		}
}
