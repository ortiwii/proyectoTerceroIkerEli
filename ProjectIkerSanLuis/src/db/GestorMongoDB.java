package db;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class GestorMongoDB {
	
	private GestorMongoDB gestorMongoDB = new GestorMongoDB();
	
	private MongoClient mongoClient = null;
	private MongoDatabase mongoDatabase = null;	
	private String stringConexion = "mongodb://localhost:27017";
	private String nombreBD = "sanluis";
	
	private GestorMongoDB () {
		MongoClientURI uri = new MongoClientURI(stringConexion);
		mongoClient = (MongoClient) new com.mongodb.MongoClient(uri);
		mongoDatabase = mongoClient.getDatabase(this.nombreBD);
	}
	public GestorMongoDB getGestorMingoDB() {
		return gestorMongoDB;
	}
	
	public void cerrarConexion() {
		if (mongoClient != null) {			
			mongoClient.close();
			mongoClient = null;
			mongoDatabase = null;
		}
	}
	
//	public boolean insertar(Archivo archivo) {
//		try {
//			MongoCollection<Document> datos = MongoBD.getDatabase().getCollection("stock");
//			
//			Document documento = new Document();
//			documento.append("_id", archivo.getNombreArchivo());
//			documento.append("idProveedor", archivo.getCodigoProveedor());
//			documento.append("contenido", archivo.getCuerpo());
//			datos.insertOne(documento);
//			return true;
//		} catch (Exception ex) {
//			System.out.println(ex);
//			return false;
//		} finally {
//			MongoBD.cerrarConexion();
//		}
//	}

}
