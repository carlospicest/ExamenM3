package curso.examen.m3.pojo;

/**
 * Clase POJO Contacto que permite mapear objetos de tipo Contacto.
 * @author Carlos
 *
 */

public class Contacto {
	
	private String nombreDestinatario;
	private String emailDestinatario;
	private String provincia;
	private String mensaje;
	
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	
	public String getEmailDestinatario() {
		return emailDestinatario;
	}
	
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
