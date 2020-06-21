package cpFinal;
import javax.servlet.annotation.WebServlet;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class mongodbConnection {
   public static  MongoCollection<org.bson.Document> getCollection(String collectionName) {
	    MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("CodePhenomRemastered");
		return database.getCollection(collectionName);
   }
}