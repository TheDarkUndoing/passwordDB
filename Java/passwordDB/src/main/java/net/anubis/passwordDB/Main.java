package net.anubis.passwordDB;
import java.io.File;
import java.util.Arrays;
import java.util.List;



import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class Main
{
  static String INPUT_FILE_PATH;
  static String OUTPUT_FILE_PATH;
  static File inputFile;
  static DatabaseHandlerMariaDB mariadb;
  static File tarFile;
  static TarArchiveInputStream tis;
  static TarArchiveEntry[] tarEntryArray;
  static String entryName;
  static int entryStartOffset;

  public static void main(final String[] args)
  {
    List<String> argsList = Arrays.asList(args);

    if(argsList.contains("-w"))
    {
       mariadb = configureWebDatabase();
      System.out.println("[MODE] Web mode engaged");

    }
    else
    {
      mariadb = configureDatabase();
    }
    if(argsList.contains("-o"))
    {
      INPUT_FILE_PATH = args[argsList.indexOf("-o") + 1];
      OUTPUT_FILE_PATH = INPUT_FILE_PATH.substring(0,INPUT_FILE_PATH.length() - 3);

      inputFile = new File(INPUT_FILE_PATH);

      tarFile = new File(OUTPUT_FILE_PATH);

      tarFile = GetFile.deCompressGZipFile(inputFile,tarFile);

      tis = GetFile.getTarArchiveStream(tarFile);
      tarEntryArray = GetFile.getEntries(tis);
      entryStartOffset = 0;
      for (int i = 0 ; i < tarEntryArray.length; i++)
      {
        entryName = tarEntryArray[i].getName();
        System.out.println(entryName);
        entryStartOffset = ParseFile.findTarEntry(tarFile,entryName);
        System.out.println(entryStartOffset);

        ParseFile.readTarEntry(tarFile,entryStartOffset,tarEntryArray[i],mariadb);
      }

    }



  }
  public static DatabaseHandlerMariaDB configureDatabase()
  {
    Utility.setupDB();
    mariadb = new DatabaseHandlerMariaDB();
    mariadb.connect("database.properties");
    return mariadb;
  }
  public static DatabaseHandlerMariaDB configureWebDatabase()
  {
    Utility.setupDB_Web();
    mariadb = new DatabaseHandlerMariaDB();
    mariadb.connect("database_web.properties");
    return mariadb;
  }
}
