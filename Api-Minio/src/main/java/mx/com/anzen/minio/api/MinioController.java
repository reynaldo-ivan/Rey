package mx.com.anzen.minio.api;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.minio.messages.Bucket;
import mx.com.anzen.minio.services.MinioService;
import mx.com.anzen.mongo.services.MongoService; 

@RestController
public class MinioController {

	// Inyeccion de dependencias del servicio
	@Autowired
	private MinioService nameService;
	
	@Autowired
	private MongoService mongo;
	
	@RequestMapping(value="/test")
    public String test(@RequestHeader(value="User") String  user ) throws UnknownHostException{ 
		 System.out.println("headers "+user);
        return "OK";
    }
	
	@RequestMapping(value="/layout")
    public Map<String,Object> layout(@RequestHeader JSONObject  json ) throws UnknownHostException{ 
		
		Map<String,Object> map=new HashMap();
		map.put("fileDefinition.idFileType",json.get("IdFileType").toString()); 
		Map<String,Object> mapResult=mongo.consulta("BancaGenerica",map); 
		  
        return mapResult;
    }
	

	@RequestMapping(value = "/consulta")
	public String insertState() {
		//nos trae todos los nodos que existen
				List<Bucket> lista= nameService.listaNodos();
				for (Bucket bucket : lista) {
			        System.out.println(bucket.creationDate() + ", " + bucket.name());
			      }
	 
		return "OK";

	}
}
