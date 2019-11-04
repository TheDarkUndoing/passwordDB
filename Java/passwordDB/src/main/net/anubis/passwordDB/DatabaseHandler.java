package net.anubis.passwordDB;
import com.mongodb.client.MongoClients;


public class DatabaseHandler
{
  private MongoClient mongoClient = MongoClients.create();
  private MongoDatabase database;
  private MongoCollection<Document> collection;

  public DatabaseHandler(String database,String collection)
  {
    database = mongoClient.getDatabase(database);
    collection = database.getCollection(collection);
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
    }
    else
    {

    }


  }
}
