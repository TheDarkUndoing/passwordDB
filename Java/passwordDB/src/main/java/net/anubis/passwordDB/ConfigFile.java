package net.anubis.passwordDB;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigFile
{
  private String path;
  private Properties config = new Properties();

  public ConfigFile(String path)
  {
    this.path = path;
  }
  public Properties load()
  {
    try (InputStream input = new FileInputStream(this.path))
    {
      //Properties config = new Properties();
      this.config.load(input);
    } catch (IOException ex)
    {
      ex.printStackTrace();
    }
    return this.config;
  }

  public void makeDBConfig()
  {
    try (OutputStream output = new FileOutputStream(this.path))
    {
      Properties prop = new Properties();

      // set the properties value
      prop.setProperty("db.url", "jdbc:mysql://localhost/password_db");
      prop.setProperty("db.username", "passwordDB");
      prop.setProperty("db.password", "passwordDB");

      // save properties to project root folder
      prop.store(output, null);

    } catch (IOException io)
      {
      io.printStackTrace();
      }
  }

  public String toString()
  {
    return config.toString();
  }
}
