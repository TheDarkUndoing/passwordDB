package net.anubis.passwordDB;

import java.sql.*;

public class Utility
{
  public static void setupDB()
  {
    String url = "jdbc:mysql://localhost/";
    String user = "root";
    String pass = "password";
    Statement st = null;
    Connection con = null;
    ResultSet res = null;
    Boolean dbUserExist = false;
    Boolean dbDatabaseExist = false;
    Boolean dbTableExist = false;




    try
    {
      con = DriverManager.getConnection(url,user, pass);
      st = con.createStatement();
      //Add USER
      res = st.executeQuery("select * from mysql.user");
      while(res.next())
      {
        String dbUser = res.getString("User");
        if(dbUser.equals("passwordDB"))
        {
          dbUserExist = true;
          System.out.println("[SETUP-DB] user found in database");
        }

      }
      if(!dbUserExist)
      {
        System.out.println("[SETUP-DB] user not found in database");
        System.out.println("[SETUP-DB] adding passwordDB user...");
        st.executeQuery("CREATE USER 'passwordDB'@'localhost' IDENTIFIED BY 'passwordDB'");
        st.executeQuery("GRANT ALL ON *.* TO 'passwordDB'@'localhost' WITH GRANT OPTION");

      }
      //Add DATABASE
      res = st.executeQuery("show databases");
      while(res.next())
      {
        String dbDatabase = res.getString("Database");
        if(dbDatabase.equals("password_db"))
        {
          dbDatabaseExist = true;
          System.out.println("[SETUP-DB] Database found in database");
        }

      }
      if(!dbDatabaseExist)
      {
        System.out.println("[SETUP-DB] Database not found in database");
        System.out.println("[SETUP-DB] adding password_db database...");
        st.executeQuery("CREATE DATABASE password_db");
      }

      //Add TABLE
      st.executeQuery("use password_db");
      res = st.executeQuery("show tables");
      while(res.next())
      {
        String dbTable = res.getString("Tables_in_password_db");
        if(dbTable.equals("password_by_user"))
        {
          dbTableExist = true;
          System.out.println("[SETUP-DB] Table found in database");
        }
      }
      if(!dbTableExist)
      {
        System.out.println("[SETUP-DB] Table not found in database");
        System.out.println("[SETUP-DB] adding password_by_user table...");
        st.executeQuery("CREATE TABLE password_db.password_by_user( pass_combos JSON );");
      }
    }catch (SQLException e)
    {
      e.printStackTrace();
    }


    //Add DATABASE

  }
}
