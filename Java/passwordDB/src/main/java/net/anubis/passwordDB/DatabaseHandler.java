package net.anubis.passwordDB;
import java.util.Arrays;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;



public class DatabaseHandler
{
  private MongoClient mongoClient = MongoClients.create();
  private MongoDatabase database;
  private MongoCollection<Document> collection;

  public DatabaseHandler(String database,String collection)
  {
    this.database = mongoClient.getDatabase(database);
    this.collection = this.database.getCollection(collection);
    System.out.println("Database: "+database+"\nCollection: "+collection+"\nCONNECTED");

  }
  public void insert(String[] passCombo)
  {
    String username = passCombo[0];
    String password = passCombo[1];
    Document wd = collection.find(eq("user",username)).first();
    if(wd == null)
    {
      wd = new Document("user",username)
              .append("pass",Arrays.asList(password))
              .append("count",1);
    }
    else
    {

    }
    this.collection.insertOne(wd);


  }
  public void close()
  {
    this.mongoClient.close();
  }
}
