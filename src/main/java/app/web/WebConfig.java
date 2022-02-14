package app.web;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//Manejo de varios idiomas (i18n) hace referencia a internationalization

@Configuration //Para que sea reconocida como un Bean
public class WebConfig implements WebMvcConfigurer {
	
	@Bean //Esta anotacion es para que lo agregue al contenedor de Spring
	public LocaleResolver localeResolver() //Crea una instancia de LocaleResolver
	{
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver(); //Creo la Sesion
		sessionLocaleResolver.setDefaultLocale(new Locale("es")); //Idioma a trabajar
		return sessionLocaleResolver;
	} //Se va a asociar con el archivo de propiedades (messages.properties)
	
	//messages.properties el archivo base pero a partir de este archivo se pueden crear otros
	//Para tener archivos de propiedades en otros lenguajes
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() // Para cambiar el idioma de manera Dinamica
	{
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor(); //Creo el interceptor
		localeChangeInterceptor.setParamName("lang"); //Se elije el Parametro para cambiar de idioma en Thymeleaf
		return localeChangeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registro) //Registra el Interceptor
	{
		registro.addInterceptor(localeChangeInterceptor()); //Cambia el registro dinamicamente
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registro) //Registra las rutas que no esten pasando por ningun controlador
	{
		registro.addViewController("/").setViewName("inicio"); //Ruta de inicio
		registro.addViewController("/login"); //Ruta de login
		registro.addViewController("/errores/403").setViewName("/errores/403"); //Ruta de acceso denegado - Roles
	}
}
