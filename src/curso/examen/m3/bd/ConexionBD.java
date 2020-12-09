package curso.examen.m3.bd;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class ConexionBD {
	
	private static Properties prop = new Properties();
	private static String fichero = "curso/examen/m3/bd/connection.properties";
	private static Connection conexion = null;
	
	public static Connection getConexion(boolean debug, boolean commit) {
		
		if (conexion == null) {
			
	    	try {
	    		
	    		try {
		        	
	    			ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    	    	URL appResourceURL = loader.getResource(fichero);
	    	    	String dbConfigFileRoute = appResourceURL.getPath();
	    	    	dbConfigFileRoute = dbConfigFileRoute.substring(1, dbConfigFileRoute.length());
	    			
	    			prop.load(new FileInputStream(dbConfigFileRoute));
	    			
	    			Class.forName("com.mysql.jdbc.Driver");
		        	conexion = (Connection) DriverManager.getConnection("jdbc:mysql://" + prop.getProperty("mysql.host") + ":" + 
		        														prop.getProperty("mysql.port") + "/" +
		        														prop.getProperty("mysql.db"), 
		        														prop.getProperty("mysql.user"), 
		        														prop.getProperty("mysql.pw"));	
		        	conexion.setAutoCommit(commit);
		    		if (debug) System.out.println("Conexión correcta a la base de datos.");
		        } catch (IOException ex) {
		        	if (debug) System.out.println("No existe el archivo de conexión.");
		        	ex.printStackTrace();
		        }
					
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				if (debug) System.err.println("No se ha podido realizar la conexión a la base de datos");
			}
	    	
		}
    	
    	return conexion;

    }
	
	public static void cerrarConexion(boolean debug) {
		
		try {
			if (conexion != null) {
	    		conexion.close();
	            conexion = null;
	            if (debug) System.out.println("La conexión a la base de datos " + prop.getProperty("mysql.db") + " ha terminado.");
			}
    	} catch (SQLException e) {
    		if (debug) System.out.println("Error al cerrar la conexion.");
        }
		
	}
	
	public static void main(String[] args) {
		//Connection cn = getConexion(true, true);
	}
	
}
