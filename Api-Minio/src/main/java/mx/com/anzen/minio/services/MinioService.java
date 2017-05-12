package mx.com.anzen.minio.services;

import java.util.ArrayList;
import java.util.List;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.messages.Bucket;
import mx.com.anzen.minio.models.Archivo;
import mx.com.anzen.minio.models.Credencial; 

public interface MinioService {
	 
	
	public boolean existeNodo(String nombreNodo);
	
	public String subirArchivo(String nodo,String ruta);
	
	public List<Bucket> listaNodos();
	
	public ArrayList<Archivo> listaArchivos(String nombre);
	
	public String crearNodo(String nombreNodo);
	
	public String eliminaNodo(String nombreNodo);
	
	public String generaUrl(String nombreNodo,String nombreArchivo, int tiempoVida);
	
	public String generaUrlPut(String nombreNodo,String nombreArchivo, int tiempoVida);
	
	public String crearUrl(String nodo,String ruta,int tiempoVida);
	
	public String eliminaArchivo(String nodo, String nombreArchivo);
	
	public ObjectStat datosArchivo(String nodo, String nombreArchivo);
	
	//Conexión con minio
	
	public Credencial credencialesPorperties();
	
	public MinioClient conexionMinio();
	  
	public Credencial servicioCredenciales(String url);
}
