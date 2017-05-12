package mx.com.anzen.minio.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Clase que nos trae las variables de el archivo properties
 *  
 * @author anzen
 *
 */

@ComponentScan
@PropertySource("classpath:/application.properties") 
public class ConexionMinioBean {
	

	public ConexionMinioBean() {
		// TODO Auto-generated constructor stub
	}
	 
	/**
	 * Atributo usado para obtener la url del servicio
	 * que nos va a traer las credenciales
	 */
	@Value("${minio.resturl}")
	private String restUrl;
	
	/**
	 * Atributo usado para obtener la url
	 */
	@Value("${minio.url}")
	private String url;
	
	/**
	 * Atributo usado para obtener el accessKey
	 */
	@Value("${minio.accessKey}")
	private String accessKey;
	 
	/**
	 * Atributo usado para obtener el secretKey
	 */
	@Value("${minio.secretKey}")
	private String secretKey;

	
	
	/**
	 * Setters and getters
	 * @return
	 */
	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	
	 

}
