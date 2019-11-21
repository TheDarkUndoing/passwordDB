package net.anubis.passwordDB;
import java.sql.*;
import java.util.Properties;

public class DatabaseHandlerMariaDB
{
  ConfigFile config = new ConfigFile("database.properties");

  String url = null;
  String password = null;
  String username = null;
  Connection con = null;
  Properties prop = null;
  Statement st = null;
  ResultSet res = null;

  public void connect()
  {
    config.makeDBConfig();
    prop = config.load();

    url = prop.getProperty("db.url");
    password = prop.getProperty("db.password");
    username = prop.getProperty("db.username");



    try
    {
      con = DriverManager.getConnection(url,username, password);
      st = con.createStatement();
    }catch (SQLException e)
    {
      e.printStackTrace();
    }

  }
  public void insert(String[] passCombo)
  {
    String username = passCombo[0];
    String password = passCombo[1];
    try
    {
      res = st.executeQuery("INSERT INTO password_db (username,passwords) VALUES ("+username+","+password+")");
    } catch(SQLException e)
    {
      e.printStackTrace();
    }



  }
}
