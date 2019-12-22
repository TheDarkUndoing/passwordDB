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
    ConfigFile.makeDBConfig("database.properties");
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
    //boolean done = false;
    String passwordList = null;
    try
    {

      res = st.executeQuery("Select username FROM password_db.password_by_user WHERE username = \'"+username+"\'");
      //If username exists already update passwords
      //res.beforeFirst();

      if(res.next() == false)// add new entry
      {
        //System.out.println(res.getString("username"));
        st.executeQuery("INSERT INTO password_db.password_by_user (username,passwords) VALUES (\'"+username+"\',\'"+password+"\')");
        System.out.println("NEW Username inserted");
      }
      else
      {
        System.out.println("Existing Username found");
        res = st.executeQuery("Select passwords FROM password_db.password_by_user WHERE username = \'"+username+"\'");

        res.next();
        passwordList = res.getString("passwords");
        if(passwordList.contains(password) == false)
        {
          System.out.println("Updating passwordlist");
          res = st.executeQuery("UPDATE password_db.password_by_user Set passwords = \'"+passwordList+","+password+"\' WHERE username = \'"+username+"\'");
        }
        else{System.out.println("Password already in row ");}
      }
      System.out.println("[LOG]Reached end of insert");
      } catch(SQLException e)
      {
        e.printStackTrace();
      }

      }





  }
