package curso.examen.m3.operations;

import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//https://logging.apache.org/log4j/1.2/apidocs/index.html
//http://programacion.jias.es/2013/03/log4j-tutorial-configuracion-rapida/
//http://migranitodejava.blogspot.com/2011/07/log4j.html
	
public class MiLog4j {
	
	private static Logger log = Logger.getLogger(MiLog4j.class);

	public static void main(String[] args) {
		
		/*ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	URL appResourceURL = loader.getResource("log4j.properties");
    	String dbConfigFileRoute = appResourceURL.getPath();
    	dbConfigFileRoute = dbConfigFileRoute.substring(1, dbConfigFileRoute.length());
		
		// Configuración a través de fichero de propiedades
		PropertyConfigurator.configure(dbConfigFileRoute);
		
		//SALIDA
		log.debug("Este es un mensaje en nivel DEBUG");
		log.info("Este es un mensaje en nivel INFO");
		/*log.warn("Este es un mensaje en nivel WARN");
		log.error("Este es un mensaje en nivel ERROR");
		log.fatal("Este es un mensaje en nivel FATAL");*/
		
		debugMsg("Hola");
		
		
	}
	
	public static void debugMsg(String msg) {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	URL appResourceURL = loader.getResource("log4j.properties");
    	String dbConfigFileRoute = appResourceURL.getPath();
    	dbConfigFileRoute = dbConfigFileRoute.substring(1, dbConfigFileRoute.length());
		
		// Configuración a través de fichero de propiedades
		PropertyConfigurator.configure(dbConfigFileRoute);
		
		log.debug(msg);
		
	}
	
}
