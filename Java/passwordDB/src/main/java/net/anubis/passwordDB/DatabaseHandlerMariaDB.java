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

  public void connect()
  {
    config.makeDBConfig();
    prop = config.load();

    url = prop.getProperty("db.url");
    password = prop.getProperty("db.password");
    username = prop.getProperty("db.username");

  

    try
    {
      connection = DriverManager.getConnection(url,username, password);
    }catch (SQLException e)
    {
      e.printStackTrace();
    }

  }
  public void insert(String[] passCombo)
  {

  }
}
