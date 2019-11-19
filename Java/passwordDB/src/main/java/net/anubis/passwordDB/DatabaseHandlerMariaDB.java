package net.anubis.passwordDB;
import java.sql.*;
import java.util.Properties;

public class DatabaseHandlerMariaDB
{
  ConfigFile config = new ConfigFile("database.properties");

  String url = null;
  String password = null;
  String username = null;
  Connection connection = null;
  Properties prop = null;

  public DatabaseHandlerMariaDB()
  {
    config.makeDBConfig();
    this.prop = config.load();
     System.out.println(this.prop.getProperty("db.url"));
    String password = prop.getProperty("db.password");
    String username = prop.getProperty("db.username");

    try
    {
      Connection connection = DriverManager.getConnection(url,username, password);
    }catch (SQLException e)
    {
      e.printStackTrace();
    }

  }
  public void insert(String[] passCombo)
  {

  }
}
