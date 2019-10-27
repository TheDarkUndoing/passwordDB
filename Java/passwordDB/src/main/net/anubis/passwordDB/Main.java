package net.anubis.passwordDB;
import java.io.File;
import java.util.Arrays;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class Main
{
  public static void main(String[] args)
  {
    try{
      GetFile GetFile = new GetFile();
        //String INPUT_FILE_PATH = "/media/user/DATA/FrostWire/Torrent
        //Data/Collection 1/Collection  #1_BTC combos.tar.gz";
        String INPUT_FILE_PATH = args[0];
        String OUTPUT_FILE_PATH = "tarfile_test.tar";
        File inputFile = new File(INPUT_FILE_PATH);

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
          entryStartOffset = parseFile.findTarEntry(tarFile,entryName);
          System.out.println(entryStartOffset);

          parseFile.readTarEntry(tarFile,entryStartOffset,tarEntryArray[i]);
        }

      } catch (IOException e)
      {
        e.printStackTrace();
      }

    //System.out.println(Arrays.toString(args));
  }
}
