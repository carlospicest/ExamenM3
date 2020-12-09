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
 * Servlet implementation class Controlador
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
		
			ContactoDAOImplMS.almacenarContacto(p1);
			
			MiLog4j.debugMsg("Enviado mensaje correctamente a " + destinatarioNombre + " con un email " + destinatarioEmail + ".");
			
			request.setAttribute("resultado", "El mensaje ha sido enviado correctamente.");
			
		} else {
			
			request.setAttribute("resultado", "El mensaje no ha podido ser enviado.");
		
		}
		
		// Cargar de nuevo las provincias para evitar el error.
		
		ArrayList<String> provincias = Util.obtenerListadoProvincias();
		request.setAttribute("listaProvincias", provincias);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
	}

}
