<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%
	ArrayList<String> listaProvincias = (ArrayList<String>) request.getAttribute("listaProvincias");

	String error = String.valueOf(request.getAttribute("resultado"));
	
	if (error.equals("null")) {
		error = "";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Examen M3 - Carlos Picado Esteban</title>
	<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
	<form action="" method="POST">
		<p>
			<label>Nombre del destinatario:</label>
			<input class="form-control" type="text" name="destinatarioNombre" value="" required/>
		</p>
		<p>
			<label>Email destinatario:</label>
			<input class="form-control" type="email" name="destinatarioEmail" value="" required/>
		</p>
		<p>
			<label>Provincia:</label>
			<select name="provincia" class="form-control">
				<option value="default" selected>Escoger provincia</option>
				<% for (int i=0;i<listaProvincias.size();i++) { %>
					<option value="<%= listaProvincias.get(i).toString() %>"><%= listaProvincias.get(i).toString() %></option>
				<% } %>
			</select>
		</p>
		<p>
			<label>Mensaje:</label>
			<textarea name="cuerpoMensaje" rows="4" cols="50" required></textarea>
		</p>
		<p>
			<input class="btn btn-warning" type="submit" name="enviar" value="Enviar correo"/>
		</p>
	</form>
	
	<label id="resultado"><%= error %></label>
	
</body>
</html>