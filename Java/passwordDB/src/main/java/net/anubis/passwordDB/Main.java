package net.anubis.passwordDB;
import java.io.File;
import java.util.Arrays;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class Main
{
  static String INPUT_FILE_PATH;
  static String OUTPUT_FILE_PATH;
  public static void main(String[] args)
  {

    INPUT_FILE_PATH = args[0];
    OUTPUT_FILE_PATH = "tarfile_test.tar";
    File inputFile = new File(INPUT_FILE_PATH);
    DatabaseHandlerMariaDB mariadb = configureDatabase();


    File tarFile = new File(OUTPUT_FILE_PATH);
    tarFile = GetFile.deCompressGZipFile(inputFile,tarFile);

    TarArchiveInputStream tis = GetFile.getTarArchiveStream(tarFile);
    TarArchiveEntry[] tarEntryArray = GetFile.getEntries(tis);
    String entryName =null;
    int entryStartOffset = 0;
    for (int i = 0 ; i < tarEntryArray.length; i++)
    {
      entryName = tarEntryArray[i].getName();
      System.out.println(entryName);
      entryStartOffset = ParseFile.findTarEntry(tarFile,entryName);
      System.out.println(entryStartOffset);

      ParseFile.readTarEntry(tarFile,entryStartOffset,tarEntryArray[i],mariadb);
    }
  }
  public static DatabaseHandlerMariaDB configureDatabase()
  {
    Utility.setupDB();
    DatabaseHandlerMariaDB mariadb = new DatabaseHandlerMariaDB();
    mariadb.connect();
    return mariadb;
  }
}
