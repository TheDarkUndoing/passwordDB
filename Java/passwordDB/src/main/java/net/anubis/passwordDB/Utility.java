import java.sql.*;

public class Utility
{
  public static void setupDB()
  {
    String url = "jdbc:mysql://localhost/";
    String user = "root";
    String pass = "pass";
    Statement stmnt = null;
    try
    {
      Connection connection = DriverManager.getConnection(url,user, pass);
      
    }catch (SQLException e)
    {
      e.printStackTrace();
    }
    //Add USER

    //Add DATABASE

  }
}
