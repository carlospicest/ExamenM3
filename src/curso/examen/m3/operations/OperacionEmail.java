package curso.examen.m3.operations;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import curso.examen.m3.libreria.Util;

public class OperacionEmail {
	
	/**
	 * Clase que realiza mediante el uso de la libreria Utils.enviarEmail(), el envío de un correo
	 * en función de los parámetros que se le pasen a la función.
	 * Esta función lee un archivo llamado email.properties que contiene el nombre de usuario y la
	 * contraseña del correo que enviará el mensaje a un destinatario.
	 * @param emailDestino
	 * @param asuntoEmail
	 * @param cuerpoEmail
	 * @return boolean (Devuelve true si el email ha sido enviado o false si no ha podido ser enviado).
	 */
	
	public static boolean enviarEmail(String emailDestino, String asuntoEmail, String cuerpoEmail) {
		
		boolean resultado = false;
		
		Properties prop = new Properties();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	URL appResourceURL = loader.getResource("curso/examen/m3/operations/email.properties");
    	String emailFilePath = appResourceURL.getPath();
    	emailFilePath = emailFilePath.substring(1, emailFilePath.length());
		
		try {
			
			prop.load(new FileInputStream(emailFilePath));
			
			String emailUser = prop.getProperty("email.user");
			String passUser = prop.getProperty("email.pass");
			
			//System.out.println("DEBUG emailUser: " + emailUser + " passUser: " + passUser);
			
			resultado = Util.enviarEmail(emailUser, passUser, emailDestino, asuntoEmail, cuerpoEmail);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	
	public static void main(String[] args) {
		System.out.println("Resultado envio correo: " + enviarEmail("cpe17494@gmail.com", "Va o no va???", "Es solo una prueba"));
	}
	
}
