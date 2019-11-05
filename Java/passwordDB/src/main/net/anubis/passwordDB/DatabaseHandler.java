package net.anubis.passwordDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;


public class DatabaseHandler
{
  private MongoClient mongoClient = MongoClients.create();
  private MongoDatabase database;
  private MongoCollection<Document> collection;

  public DatabaseHandler(String database,String collection)
  {
    database = mongoClient.getDatabase(database);
    collection = database.getCollection(collection);
    System.out.println("Database: "+database+"\nCollection: "+collection+"\nCONNECTED");

  }
  public void insert(String[] passCombo)
  {
    String username = passCombo[0];
    String password = passCombo[1];
    Document wd;

    if(wd = collection.find(eq("user",username)).first() == null)
    {
      wd = new Document("user",username)
              .append("pass",Arrays.asList(password))
              .append("count",1);
    }
    else
    {

    }


  }
}
