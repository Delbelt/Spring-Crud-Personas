package app.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.UsuarioDao;
import app.datos.Rol;
import app.datos.Usuario;
import lombok.var;

@Service("userDetailsService") //Importante respetar este nombre
public class UsuarioService implements UserDetailsService {
	
//En este caso tiene que ser una Clase para poder implementar el UserDetailsService	
	
	@Autowired
	private UsuarioDao dao;
	
	@Override
	@Transactional(readOnly=true)
	//Carga la implementacion correspondiente segun el usuario que hemos proporcionado
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Usuario usuario = dao.findByUsername(username); //Trae al usuario con el metodo creado en UsuarioDao
		
		if(usuario == null) //Si no se encuentra al usuario
		throw new UsernameNotFoundException("Usuario no encontrado: "+ usuario);
		
		var roles = new ArrayList<GrantedAuthority>(); //Spring solicita que sea de tipo: GrantedAuthority		
		
		for (Rol rol : usuario.getRoles()) //Recorro los roles del usuario
		{
			roles.add(new SimpleGrantedAuthority(rol.getNombre())); //Los agrego al GrantedAuthority (roles)
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), roles);
		//User es una clase de Spring Segurity (nombre, password, roles)
	}
}
