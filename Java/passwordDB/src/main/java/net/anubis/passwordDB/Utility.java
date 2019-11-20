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
        if(dbDatabase.equals("passwordDB"))
        {
          dbDatabaseExist = true;
          System.out.println("[SETUP-DB] Database found in database");
        }

      }
      if(!dbUserExist)
      {
        System.out.println("[SETUP-DB] Database not found in database");
        System.out.println("[SETUP-DB] adding passwordDB database...");
        st.executeQuery("CREATE DATABASE passwordDB");
      }

    }catch (SQLException e)
    {
      e.printStackTrace();
    }


    //Add DATABASE

  }
}
