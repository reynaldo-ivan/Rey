package mx.com.anzen.genericbank.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import mx.com.anzen.mongo.models.ConexionDataBean;
import mx.com.anzen.repository.SPRepository;
 
@ComponentScan("mx.com.anzen.mongo")
@Configuration
public class MenuAppConfig {
	
	@Bean
	public ConexionDataBean conexion(){
		return new ConexionDataBean();
	}
	
	// Realizar la inyección de dependencia para el repositorio de persistencia
		@Bean
	    public SPRepository entityManager() {
	        return new SPRepository();
	    }
	@Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/Layout").allowedOrigins("http://54.215.247.221:9092");
	            	registry.addMapping("/Menu").allowedOrigins("*");
	            }
	        };
	    }
	
}
