import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFile
{
  private String path;
  private Properties config;

  public ConfigFile(String path)
  {
    this.path = path;
  }
  public Properties loadConfig()
  {
    try (InputStream input = new FileInputStream(this.path))
    {
      Properties config = new Properties();
      config.load(input);

    } catch (IOException ex)
    {
      ex.printStackTrace();
    }
    return config;
  }
}
