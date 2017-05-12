package mx.com.anzen.mongo.services;
 
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import mx.com.anzen.mongo.config.ConfigBean; 
import mx.com.anzen.mongo.models.ConexionDataBean;
import mx.com.anzen.mongo.models.DataBean;
/**
 * Clase que contiene los metodos que realizan las insercion y la consulta a la base de datos.
 * @author anzen
 *
 */
@Repository
@Service
public class MongoServiceImpl implements MongoService{
 
	/**
	 * Inyection de ConexionBean para hacer uso de los valores de los atributos creados en el
	 * archivo properties.
	 */
	@Autowired
	private ConexionDataBean conexion;
	
	
	ApplicationContext appContext; 
	DataBean data=null; 
	
	/**
	 * Metodo que realiza el alta de datos hacia la base de datos configurada.
	 * @param nombreDB
	 * @param nombreTabla
	 * @param map
	 * @return
	 */
	public String insert(String nombreTabla, Map<String,Object> map){
		  
		ApplicationContext appContext;
		appContext=new AnnotationConfigApplicationContext(ConfigBean.class);
		DataBean data=(DataBean) appContext.getBean("iconexionbd");
		
        DB db=conexion().getDB(conexion.getDatabase());
        DBCollection table= db.getCollection(nombreTabla); 
  
        Iterator it = map.keySet().iterator();
		while(it.hasNext()){
		  Object key = it.next();
		  data.getObjectAlta().put(key.toString(), map.get(key));
		} 
		
		try{ 
			table.insert(data.getObjectAlta());
			((ConfigurableApplicationContext) appContext).close();
		}catch (Exception e) {
			return "Error: "+e.getMessage();
		}
		
		 return "OK";
	}
	
 

/**
 * Realiza una consulta donde le mandamos un mapa de los datos que no 
 * queremos traer.
 */

public Map<String,Object> consult(String nombreTabla,
		Map<String,Object> map,Map<String,Object> mapProyect){
	 
	ApplicationContext appContext;
	appContext=new AnnotationConfigApplicationContext(ConfigBean.class); 
	DataBean data=(DataBean) appContext.getBean("iconexionbd");
	  
	DB db= conexion().getDB(conexion.getDatabase());  
    DBCollection table= db.getCollection(nombreTabla); 
    
    Map<String,Object> mapa= new HashMap<String, Object>(); 
    Iterator it = map.keySet().iterator();
	while(it.hasNext()){
	  Object key = it.next(); 
	  data.getObjectAlta().put(key.toString(), map.get(key)); 
	}
	
	Iterator itProyect = mapProyect.keySet().iterator();
	while(itProyect.hasNext()){
	  Object keyProyect = itProyect.next(); 
	  data.getObjectProyect().put(keyProyect.toString(), mapProyect.get(keyProyect)); 
	}
	
    DBCursor cursor; 
    try{  
    	System.out.println("mapa a mandar  "
    +data.getObjectAlta()+","+data.getObjectProyect());
    	cursor=table.find(data.getObjectAlta(),data.getObjectProyect());  
        while(cursor.hasNext()) {
        	DBObject key = cursor.next(); 
        	Set<String> keyset=key.keySet();
        	for (String s: keyset){ 
            			mapa.put(s,key.get(s));  
        	}
        }
        ((ConfigurableApplicationContext) appContext).close(); 
    	
    }catch (Exception e) {
		System.out.println("Error: "+e.getMessage());
	}
    
	 return mapa;
}


public Map<String,Object> consult(String nombreTabla,Map<String,Object> map){
	 
	ApplicationContext appContext;
	appContext=new AnnotationConfigApplicationContext(ConfigBean.class); 
	DataBean data=(DataBean) appContext.getBean("iconexionbd");
	  
	DB db= conexion().getDB(conexion.getDatabase());  
    DBCollection table= db.getCollection(nombreTabla); 
    
    Map<String,Object> mapa= new HashMap<String, Object>(); 
    Iterator it = map.keySet().iterator();
	while(it.hasNext()){
	  Object key = it.next(); 
	  data.getObjectAlta().put(key.toString(), map.get(key)); 
	}
	 
	
    DBCursor cursor; 
    try{  
    	System.out.println("mapa a mandar  "
    +data.getObjectAlta()+","+data.getObjectProyect());
    	cursor=table.find(data.getObjectAlta());  
        while(cursor.hasNext()) {
        	DBObject key = cursor.next(); 
        	Set<String> keyset=key.keySet();
        	for (String s: keyset){ 
            			mapa.put(s,key.get(s));  
        	}
        }
        ((ConfigurableApplicationContext) appContext).close(); 
    	
    }catch (Exception e) {
		System.out.println("Error: "+e.getMessage());
	}
    
	 return mapa;
}

	
	/**
	 * Clase que realiza la conexion a mongo.
	 * @return MongoClient
	 */
	
	public MongoClient conexion() { 
		
		 appContext=new AnnotationConfigApplicationContext(ConfigBean.class); 
		 data=(DataBean) appContext.getBean("iconexionbd");  
		 
		
			try {   
				data.setMongo(new MongoClient(conexion.getHost(),
						Integer.parseInt(conexion.getPuerto())));
	 
			} catch (UnknownHostException e) { 
				System.out.println("Error: "+e.getMessage());
			} catch (MongoException e) { 
				System.out.println("Error: "+e.getMessage());
			} catch (IOException e) { 
				System.out.println("Error: "+e.getMessage());
			}
			
			return data.getMongo();
	}
	
}
