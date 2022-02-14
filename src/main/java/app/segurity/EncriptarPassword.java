package app.segurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
	
	//Para codificar contraseñas

	public static void main(String[] args) {
		
		String password = "123";
		
		System.out.println(encriptar(password));
	}
	
	public static String encriptar(String password)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}