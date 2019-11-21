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
    String dbUser = null;
    String dbDatabase = null;
    String dbTable = null;
    String dbIndex = null;
    Boolean dbUserExist = false;
    Boolean dbDatabaseExist = false;
    Boolean dbTableExist = false;
    Boolean dbIndexExist = false;




    try
    {
      con = DriverManager.getConnection(url,user, pass);
      st = con.createStatement();
      //Add USER
      res = st.executeQuery("select * from mysql.user");
      while(res.next())
      {
        dbUser = res.getString("User");
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
        dbDatabase = res.getString("Database");
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
        dbTable = res.getString("Tables_in_password_db");
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
        st.executeQuery("CREATE TABLE password_db.password_by_user( username varchar(255), passwords LONGTEXT );");
      }
      // Add Index
      res = st.executeQuery("SHOW INDEX FROM password_db.password_by_user");
      while(res.next())
      {
        dbTable = res.getString("Table");
        dbIndex = res.getString("Key_name");
        if(dbTable.equals("password_by_user") && dbIndex.equals("username"))
        {
          dbIndexExist = true;
          System.out.println("[SETUP-DB] Index found in database");
        }

      }
      if(!dbIndexExist)
      {
        System.out.println("[SETUP-DB] index not found in database");
        System.out.println("[SETUP-DB] adding username index...");
        st.executeQuery("CREATE INDEX username ON password_by_user (username)");


      }
    }catch (SQLException e)
    {
      e.printStackTrace();
    }




  }
}
