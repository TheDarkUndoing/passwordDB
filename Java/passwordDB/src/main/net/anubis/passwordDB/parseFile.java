package net.anubis.passwordDB;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;
public class parseFile
{

/*
Returns absoulute offset of start location of tar entry

*/
  public static int findTarEntry(File file, String entryName)throws IOException
  {
    FileInputStream fis = new FileInputStream(file);
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
    return offset;
  }
}
