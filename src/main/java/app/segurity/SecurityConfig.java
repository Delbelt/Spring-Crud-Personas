package app.segurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //Clase de configuracion Spring
@EnableWebSecurity //Habilita la seguridad Web
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//WebSecurityConfigurerAdapter
	//Para configurar los usuarios que vamos a utilizar
	
	@Autowired
	private UserDetailsService userDetailsService;	
	
	@Autowired //Para tener disponible: AuthenticationManagerBuilder
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build
		.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder()); //Codifica el password
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception //Configura las Autorizaciones (Concepto) a partir de los roles
	{
		http
		.authorizeHttpRequests()
		.antMatchers("/editar/**", "/agregarPersona/**", "/eliminar") // /** Es para TODA ruta siguiente a esa
		.hasRole("ADMIN") // ↑↑ Quien puede acceder a estas rutas
		.antMatchers("/","/traer/**") //Ingreso al inicio (publico)
		.hasAnyRole("USER", "ADMIN")//Cualquier Usuario
		.and()
		.formLogin() //Agrega el formulario de Login
		.loginPage("/login") //Ruta Login ("/")
		.and()
		.exceptionHandling().accessDeniedPage("/errores/403"); //Acceso Denegado - Ruta("/")
	}
}
