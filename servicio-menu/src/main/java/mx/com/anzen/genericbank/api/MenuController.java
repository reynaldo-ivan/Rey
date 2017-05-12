package mx.com.anzen.genericbank.api;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import mx.com.anzen.mongo.services.MongoService;

@RestController
public class MenuController {
	
	@Autowired
	private MongoService mongo;
	

private static Logger log = Logger.getLogger(MenuController.class);
	   
	@RequestMapping(value="/Menu/menu")
    public Map<String,Object> layout(@RequestBody JSONObject  json ) { 
		
		Map<String,Object> map=new HashMap(); 
		Map<String,Object> mapProyect=new HashMap(); 
		Map<String,Object> mapResult=null;
		try{
			map.put("id",json.get("id"));
			mapProyect.put("_id",0);
			System.out.println("mapa s"+map); 
			mapResult=mongo.consult("bancanet",map,mapProyect);
			
			System.out.println("resultado de mapa "+mapResult);
		}catch (HttpMessageNotReadableException e) {
			System.out.println("exception");
			log.info("Error: "+e.getMessage());
			mapResult.put("CodigoError: ",100);
			mapResult.put("Error: ",e.getMessage()); 
			 
		}catch (Exception e) {
			log.info("Error: "+e.getMessage());
			mapResult.put("CodigoError: ",100);
			mapResult.put("Error: ",e.getMessage()); 
		}
		
        return mapResult;
    }
 
}
