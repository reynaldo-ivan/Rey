package mx.com.anzen.minio.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.MinioException;
import io.minio.errors.NoResponseException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import mx.com.anzen.minio.models.Archivo;
import mx.com.anzen.minio.models.ConexionMinioBean;
import mx.com.anzen.minio.models.Credencial;

@Repository
@Service
public class MinioServiceImpl implements MinioService{
	
	
	@Autowired
	private ConexionMinioBean conexion;
  
	/**
	 * Valida si existe el nombre del nodo dado.
	 */
	public boolean existeNodo(String nombreNodo){
		
		MinioClient minioClient=conexionMinio();
		
		boolean resultado=false;
		try {
			resultado = minioClient.bucketExists(nombreNodo);
			
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvalidBucketNameException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InsufficientDataException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NoResponseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ErrorResponseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InternalException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		 
		return resultado;
	}
	
	
	/**
	 * sube el archivo en el server minio
	 */
	
	public String subirArchivo(String nodo,String ruta){
		String resultado="";
		 
		File archivo = new File(ruta); 
		 try {
			 
			 MinioClient minioClient=conexionMinio();
		        
		      try {
				minioClient.putObject(nodo,archivo.getName(),ruta);
				resultado="Ok";
			} catch (InvalidKeyException e) {
				resultado="Error: "+e.getMessage(); 
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} 
	  } catch(MinioException e) {
	    	resultado="Error: "+e.getMessage();
	  }
		 
		 return resultado;
	}

	
	/**
	 * Nos da una lista de los nodos que existen
	 */

	public List<Bucket> listaNodos() {
		 
		List<Bucket> bucketList = null;
		 try { 
			try {
				
				MinioClient minioClient=conexionMinio();
				 
				 
			     bucketList = minioClient.listBuckets();
			} catch (InvalidKeyException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		return bucketList;
	}


	/**
	 * NOs trae una lista de los archivos dando el nombre del nodo
	 */
	public ArrayList<Archivo> listaArchivos(String nombre) {
			
		Iterable<Result<Item>> myObjects = null;
		ArrayList<Archivo> archivos=new ArrayList<Archivo>();
			
		 MinioClient minioClient=conexionMinio();
		      
		        
		try {
			myObjects = minioClient.listObjects(nombre);
			
			
			for (Result<Item> result : myObjects) {
		          Item item;
				try {
					Archivo archivo=new Archivo();
					item = result.get(); 
					 
					archivo.setFecha(item.lastModified());
					archivo.setNumeroArchivos(item.size());
					archivo.setNombreArchivo(item.objectName());
					  
					archivos.add(archivo); 
				} catch (InvalidKeyException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (InvalidBucketNameException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (InsufficientDataException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (NoResponseException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (ErrorResponseException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (InternalException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				}
		          
		     }
					
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
		         
		 
		return archivos;
	}

	/**
	 * Nos permite crear el nodo
	 */

	public String crearNodo(String nombreNodo) {
		String resultado="";
		 try {
		       
			 MinioClient minioClient=conexionMinio();
			 
		      boolean found;
			try {
				found = minioClient.bucketExists(nombreNodo);
				 
				if (found) {
					resultado="ya existe";
			      } else { 
			        minioClient.makeBucket(nombreNodo);
			        resultado="Ok";
			      }
			} catch (InvalidKeyException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		    	resultado="Error: "+e.getMessage();
		    }
		return resultado;
	}
	
	/**
	 * 
	 * @param nombreNodo
	 * @return
	 * 
	 * Nos permite eliminar en nodo
	 * 
	 */
	public String eliminaNodo(String nombreNodo){
		String resultado="";
		try {
			 MinioClient minioClient=conexionMinio();

		      boolean found;
			try {
				found = minioClient.bucketExists(nombreNodo);
				if (found) {
			        minioClient.removeBucket(nombreNodo);
			        resultado="El nodo fue eliminado";
			      } else {
			    	resultado="El nodo fue eliminado";
			      }
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		    	System.out.println("Error: "+e.getMessage());
		    }
		return resultado;
	}
	
	/**
	 * 
	 * @param nombreNodo
	 * @param nombreArchivo
	 * @param tiempoVida
	 * @return url
	 * 
	 * nos genera una url 
	 * 
	 * 
	 */
	public String generaUrl(String nombreNodo,String nombreArchivo, int tiempoVida){
		String url = null;
		try { 
			MinioClient minioClient=conexionMinio();  
			   
		    try {
				url = minioClient.presignedGetObject(nombreNodo, nombreArchivo,tiempoVida);
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}  
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e.getMessage());
		    }
		  
		return url;
	}
	
	
	
	/**
	 * 
	 * @param nombreNodo
	 * @param nombreArchivo
	 * @param tiempoVida
	 * @return
	 * 
	 * genera la url usando el metodo Put
	 */
	
	
	public String generaUrlPut(String nombreNodo,String nombreArchivo, int tiempoVida){
		String url = null;
		try { 
			MinioClient minioClient=conexionMinio();   

			
		       try {
				url = minioClient.presignedPutObject(nombreNodo,nombreArchivo,tiempoVida);
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
		       
		    } catch (MinioException e) {
		      System.out.println("Error : " + e.getMessage());
		    }
		return url;
	}
	
	
	
	/**
	 * metodo que nos sube el archivo, valida si existe el archivo, si no existe el nodo lo crea y sube el archivo
	 */
	
	public String crearUrl(String nodo,String ruta,int tiempoVida){
		String url=null;
		
		File archivo = new File(ruta); 
		
		String nodoCrea=crearNodo(nodo);
		
		if("ya existe".equalsIgnoreCase(nodoCrea)||"Ok".equalsIgnoreCase(nodoCrea)){
			
			String resultado=subirArchivo( nodo, ruta); 
			if(resultado.equalsIgnoreCase("Ok")){
				url=generaUrl(nodo, archivo.getName(), tiempoVida);
				
//				String respuestaElimina=eliminaArchivo(nodo,archivo.getName());
//				System.out.println("Eliminacion: "+respuestaElimina);
				
			}else{
				url=resultado;
			}
		}
		  
		return url;
		
	}
	
	
	/**
	 * elimina el archivo. pasandole como parametro el nombre del nodo y el nombre del archivo.
	 */
	public String eliminaArchivo(String nodo, String nombreArchivo){
		String resultado="";
		try { 
			MinioClient minioClient=conexionMinio();  
			
			  try {
				minioClient.removeObject(nodo,nombreArchivo);
				resultado="Ok";
				
			} catch (InvalidKeyException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} 
		    } catch (MinioException e) {
		    	resultado=e.getMessage();
		    }
		return resultado;
	}
	
	
	/**
	 * nos trae los datos de el archivo pasandole como parametro el nombre del nodo y el nombre del archivo.
	 */
	public ObjectStat datosArchivo(String nodo, String nombreArchivo){
		ObjectStat objectStat=null;
		 
		MinioClient minioClient=conexionMinio();  
		
		try {
		      try {
				objectStat = minioClient.statObject(nodo, nombreArchivo);
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} 
		    } catch (MinioException e) {
		    	System.out.println("Error: "+e.getMessage());
		    }
		 
		return objectStat;
		
	}
	

	/**
	 * Nos trae los atributos de el archivo properties
	 */
    public Credencial credencialesPorperties(){
    	
    	Credencial credencial=new Credencial(); 
    	 try {
    		 	System.out.println("Conexion url "+conexion.getRestUrl());
    	        credencial.setUrlRest(conexion.getRestUrl());
    	        credencial.setUrl(conexion.getUrl());
    	        credencial.setAccessKey(conexion.getAccessKey());
    	        credencial.setSecretKey(conexion.getSecretKey());
    	        

    	    } catch (Exception ex) {
    	        ex.printStackTrace();
    	    }  
    	 return credencial;
    }
    
	
	  /**
	   * Realiza la conexion hacia minio
	   */
	public MinioClient conexionMinio(){ 
		Credencial credenciales= credencialesPorperties();
    	  
		MinioClient minioClient=null;  
		Credencial credencial=null; 
		try {
			if(!credenciales.getUrlRest().equalsIgnoreCase("")){ 
				
				credencial=servicioCredenciales(credenciales.getUrlRest());
				System.out.println("url rest "+credencial.getUrl());
				minioClient = new MinioClient(credencial.getUrl(),
											  credencial.getSecretKey(),
											  credencial.getAccessKey()); 
				  
			}else{ 
				System.out.println("url "+credenciales.getUrl());
				minioClient = new MinioClient(credenciales.getUrl(),
						  credenciales.getAccessKey(),
						  credenciales.getSecretKey()
						  );
			}
		 
		    } catch (MinioException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		
//		((ConfigurableApplicationContext) appContext).close();
		return minioClient;
	}
	  
	/**
	 * Consume el servicio rest
	 */
	public Credencial servicioCredenciales(String datUrl) {
		
		Object object=null;
		JSONArray arrayObj=null;
		String output=null;  
		String json="";
		JSONParser jsonParser=null;
		Credencial credencial=null;
		
		 try {
				URL url = new URL(datUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
 
				while ((output = br.readLine()) != null) { 
					json=json+output; 
				}     
				jsonParser=new JSONParser();
				 
				try {
					credencial=new Credencial();
					object=jsonParser.parse(json.toString());  
					arrayObj=(JSONArray) object;  
					JSONObject obj2 = (JSONObject)arrayObj.get(0);  
					credencial.setAccessKey(obj2.get("secretkey").toString());
					credencial.setSecretKey(obj2.get("accesskey").toString());
					credencial.setUrl(obj2.get("url").toString());
					 
				} catch (ParseException e) { 
					System.out.println("Error: "+e.getMessage());
				}
				conn.disconnect(); 
			  } catch (MalformedURLException e) { 
				System.out.println("Error: "+e.getMessage()); 
			  } catch (IOException e) { 
				System.out.println("Error: "+e.getMessage());

			  }
 
		return credencial;
	}
	
}
