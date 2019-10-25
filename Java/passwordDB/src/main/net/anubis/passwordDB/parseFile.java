package net.anubis.passwordDB;
import java.io.IOException;
//import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

public class parseFile
{

/*
Returns absoulute offset of start location of tar entry

*/
  public static int findTarEntry(File tarfile, String entryName)throws IOException
  {
    FileInputStream fis = new FileInputStream(tarfile);
    byte[] readArea;
    byte[] entryNameBytes;
    int namelength = entryName.length();


    if (namelength % 2 == 0)
    {
      readArea = new byte[namelength];
      entryNameBytes = entryName.getBytes();
    }
    else
    {
      entryName = entryName.substring(0, entryName.length() -1);
      namelength = entryName.length();

      readArea = new byte[namelength];
      entryNameBytes = entryName.getBytes();
    }

    String readAreaString = "";
    //String entryNameBytesString = Arrays.toString(entryNameBytes);
    String entryNameBytesString = new String(entryNameBytes, StandardCharsets.UTF_8);
    int offset = 0;


    while(!Arrays.equals(readArea,entryNameBytes))
    {
      offset += fis.read(readArea);
      readAreaString = new String(readArea , StandardCharsets.UTF_8);

      //readAreaString = Arrays.toString(readArea);
    //  System.out.println(entryName+" "+ readAreaString);
    //  System.out.println("readArea\t"+readAreaString);
      //System.out.println("entryNameBytes\t"+entryNameBytesString);

    }
    //To move back to the beginning of the entry
    offset -= namelength;

    return offset;
  }

  public static void readTarEntry(File tarFile, int entryStartOffset, TarArchiveEntry tarEntry) throws IOException
  {
    FileInputStream fis = new FileInputStream(tarFile);
    long entrySize = tarEntry.getSize();
    //Skips to beginning of entryand then jumps 512 bytes to start of entry's data
    //TAR Entries are 512 byte entries
    //TODO Reasearch TAR format and find distance from name field to data
    fis.skip(entryStartOffset+512);


    // while(bytesRead < entrySize )
    // {
    //   break;
    // }


  }
}
