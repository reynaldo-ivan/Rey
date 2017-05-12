package mx.com.anzen.mongo.models;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;  
 
/**
 * Clase que contiene la declaración de los atributos usados en la API.
 * @author anzen
 *
 */
public class DataBean {
	 
	/**
	 * Delcaración de los diferentes atributos.
	 * 
	 * Objetivo:
	 * - El objetivo de la creación de esta clase es para la declaración
	 *   de los atributos que estaremos usando y no crearlos varias veces.
	 */
	private MongoClient mongo; 
	private DB basedatos;
	private DBCollection table;
	private BasicDBObject searchQuery;
	private DBCursor cursor;
	private BasicDBObject obj; 
	private BasicDBObject objectAlta=new BasicDBObject();
	
	private BasicDBObject objectProyect=new BasicDBObject();
		
	
	/**
	 * Setters y getters.
	 * @return
	 */
 
	public BasicDBObject getObjectAlta() {
		return objectAlta;
	}

	public void setObjectAlta(BasicDBObject objectAlta) {
		this.objectAlta = objectAlta;
	}
 
	
	
	public BasicDBObject getObjectProyect() {
		return objectProyect;
	}

	public void setObjectProyect(BasicDBObject objectProyect) {
		this.objectProyect = objectProyect;
	}

	public MongoClient getMongo() {
		return mongo;
	}

	public void setMongo(MongoClient mongo) {
		this.mongo = mongo;
	} 
	public DB getBasedatos() {
		return basedatos;
	}

	public void setBasedatos(DB basedatos) {
		this.basedatos = basedatos;
	}

	public DBCollection getTable() {
		return table;
	}

	public void setTable(DBCollection table) {
		this.table = table;
	}

	public BasicDBObject getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(BasicDBObject searchQuery) {
		this.searchQuery = searchQuery;
	}

	public DBCursor getCursor() {
		return cursor;
	}

	public void setCursor(DBCursor cursor) {
		this.cursor = cursor;
	}

	public BasicDBObject getObj() {
		return obj;
	}

	public void setObj(BasicDBObject obj) {
		this.obj = obj;
	}


}
