import java.sql;

public class DatabaseHandlerMariaDB
{
  ConfigFile prop = new ConfigFile("resources/database.properties");
  Connection connection = DriverManager.getConnection();
}
