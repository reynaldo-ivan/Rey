package mx.com.anzen.mongo.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Clase que nos trae las variables de el archivo properties
 * 
 * - Host
 * - Puerto
 * - base de datos
 * @author anzen
 *
 */

@ComponentScan
@PropertySource("classpath:/application.properties")
//@ConfigurationProperties(prefix = "conexion")
public class ConexionDataBean {
	
	
	public ConexionDataBean(String host, String puerto,String database) {
		super();
		this.host = host;
		this.puerto = puerto; 
		this.database=database;
	}

	public ConexionDataBean() {
		
	}
	/**
	 * Atributo usado para obtener el host del properties
	 */
	@Value("${host}")
	private String host;
	
	/**
	 * Atributo usado para obtener el port del properties
	 */
	@Value("${port}")
	private String puerto;
	
	/**
	 * Atributo usado para obtener el database del properties
	 */
	@Value("${database}")
	private String database;
	 
	
	/**
	 * Setters and getters
	 * @return
	 */
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	} 

}
