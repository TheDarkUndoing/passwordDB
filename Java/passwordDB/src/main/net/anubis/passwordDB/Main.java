package net.anubis.passwordDB;
import java.io.File;
import java.util.Arrays;
import java.io.IOException;

public class Main
{
  public static void main(String[] args)
  {
    try{
      GetFile GetFile = new GetFile();
        String INPUT_FILE_PATH = "/media/user/DATA/FrostWire/Torrent Data/Collection 1/Collection  #1_BTC combos.tar.gz";
        String OUTPUT_FILE_PATH = "tarfile_test.tar";
        File inputFile = new File(INPUT_FILE_PATH);
        File tarFile = new File(OUTPUT_FILE_PATH);
        //tarFile = GetFile.deCompressGZipFile(inputFile,tarFile);
        System.out.println("Test");
        GetFile.readTarArchive(tarFile);
      } catch (IOException e)
      {
        e.printStackTrace();
      }

    //System.out.println(Arrays.toString(args));
  }
}
