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
Figure out byte reading and conversion to char
*/
  public static int findTarEntry(File file, String entryName)throws IOException
  {
    FileReader fis = new FileReader(file);
    int namelength = entryName.length();
    char[] readArea = new char[namelength];
    String readAreaString = null;
    int offset = 0;

    while(readAreaString != entryName)
    {
      offset += fis.read(readArea);
      //readAreaString = new String(readArea , StandardCharsets.UTF_8);
      readAreaString = Arrays.toString(readArea);
      System.out.println(readAreaString);

    }
    return offset;
  }
}
