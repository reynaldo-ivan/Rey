package mx.com.anzen.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.com.anzen.mongo.models.DataBean;
 
/**
 * Clase que contiene los beans para la configuración de Spring.
 * @author anzen
 *
 */
@Configuration
public class ConfigBean {
	/**
	 * Bean creado para poder usar los atributos creados en DataBean
	 * @return
	 */
	@Bean(name="iconexionbd")
	public DataBean conexionBean(){
		
		return new DataBean();
	}
	
}
