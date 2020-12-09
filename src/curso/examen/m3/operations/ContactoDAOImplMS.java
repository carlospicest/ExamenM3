package curso.examen.m3.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import curso.examen.m3.bd.ConexionBD;
import curso.examen.m3.pojo.Contacto;

public class ContactoDAOImplMS {
	
	public static boolean almacenarContacto(Contacto contacto) {
		
		boolean resultado = false;
		
		Connection conexion = ConexionBD.getConexion(true, true);
		String query = "INSERT INTO envio (ID, NOMBRE_DESTINATARIO, EMAIL_DESTINATARIO, PROVINCIA_DESTINATARIO, MENSAJE_DESTINATARIO) VALUES (?, ?, ?, ?, ?);";

		try {
			
			PreparedStatement bdContacto = conexion.prepareStatement(query);
			
			bdContacto.setString(1, null);
			bdContacto.setString(2, contacto.getNombreDestinatario());
			bdContacto.setString(3, contacto.getEmailDestinatario());
			bdContacto.setString(4, contacto.getProvincia());
			bdContacto.setString(5, contacto.getMensaje());
			
			bdContacto.executeUpdate();
			
			resultado = true;
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(true);
		}
		
		return resultado;
		
	}
	
	public static void main(String[] args) {
		
		Contacto c1 = new Contacto();
		c1.setNombreDestinatario("Carlos Picado");
		c1.setEmailDestinatario("cpe17494@gmail.com");
		c1.setProvincia("Zamora");
		c1.setMensaje("Probando funcionalidad.");
		
		System.out.println(almacenarContacto(c1));
		
	}
	
}
