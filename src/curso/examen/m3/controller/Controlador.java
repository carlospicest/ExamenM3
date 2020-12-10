package curso.examen.m3.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.examen.m3.libreria.Util;
import curso.examen.m3.operations.ContactoDAOImplMS;
import curso.examen.m3.operations.MiLog4j;
import curso.examen.m3.operations.OperacionEmail;
import curso.examen.m3.pojo.Contacto;

/**
 * Controlador que en el método GET, redirige a la vista index.jsp y carga el select
 * de provincias.
 * 
 * El método POST, envía un correo a un destinatario, almacena información en la base
 * de datos y muestra al usuario el resultado de la operación.
 */
@WebServlet("")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getRequestURL() + " - " + request.getContextPath());
		
		ArrayList<String> provincias = Util.obtenerListadoProvincias();
		
		request.setAttribute("listaProvincias", provincias);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean resultado = false;
		String destinatarioEmail = request.getParameter("destinatarioEmail");
		String destinatarioNombre = request.getParameter("destinatarioNombre");
		String provincia = request.getParameter("provincia");
		String asunto = destinatarioNombre + " - " + provincia;
		String mensaje = request.getParameter("cuerpoMensaje");
		
		resultado = OperacionEmail.enviarEmail(destinatarioEmail, asunto, mensaje);
		
		if (resultado) {
			
			Contacto p1 = new Contacto();
			
			p1.setNombreDestinatario(destinatarioNombre);
			p1.setEmailDestinatario(destinatarioEmail);
			p1.setProvincia(provincia);
			p1.setMensaje(mensaje);
			
			try {
				ContactoDAOImplMS.almacenarContacto(p1);
			} catch(Exception e) {
				System.out.println("El usuario no ha podido ser registrado en la base de datos.");
				MiLog4j.debugMsg("El usuario no ha podido ser registrado en la base de datos.");
			}
			
			MiLog4j.debugMsg("Enviado mensaje correctamente a " + destinatarioNombre + " con un email " + destinatarioEmail + ".");
			
			request.setAttribute("resultado", "<p><div class='alert alert-success'>El mensaje ha sido enviado correctamente.</div></p>");
			
		} else {
			
			request.setAttribute("resultado", "<p><div class='alert alert-danger'>El mensaje no  ha podido ser enviado.</div></p>");
		
		}
		
		// Cargar de nuevo las provincias para evitar el error.
		
		ArrayList<String> provincias = Util.obtenerListadoProvincias();
		request.setAttribute("listaProvincias", provincias);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
	}

}
