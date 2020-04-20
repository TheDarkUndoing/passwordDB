package net.anubis.passwordDB;
import java.io.IOException;
//import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

public class ParseFile
  {

  /*
  Returns absoulute offset of start location of tar entry
  TODO increase effeciency of findTar
  entry so that it doesnt parse entire file every time
  */
    public static int findTarEntry(final File tarfile, String entryName) {
    FileInputStream fis;
    byte[] readArea;
    byte[] entryNameBytes;
    int namelength = entryName.length();
    int offset = 0;

    try {
      fis = new FileInputStream(tarfile);
      if (namelength % 2 == 0) {
        readArea = new byte[namelength];
        entryNameBytes = entryName.getBytes();
      } else {
        entryName = entryName.substring(0, entryName.length() - 1);
        namelength = entryName.length();

        readArea = new byte[namelength];
        entryNameBytes = entryName.getBytes();
      }

      String readAreaString;
      // String entryNameBytesString = Arrays.toString(entryNameBytes);


      while (!Arrays.equals(readArea, entryNameBytes)) {
        offset += fis.read(readArea);
        readAreaString = new String(readArea, StandardCharsets.UTF_8);

      }
      // To move back to the beginning of the entry
      offset -= namelength;
      fis.close();
      return offset;
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return offset;

  }

  public static void readTarEntry(final File tarfile, final int entryStartOffset, final TarArchiveEntry tarEntry,
      final DatabaseHandlerMongo mongodb) {
    FileInputStream fis;
    final long entrySize = tarEntry.getSize();
    long bytesRead = 0;
    String string = null;
    byte[] line = new byte[0];
    String[] passCombo = null;
    int lineCount = 0;

    // byte[] readbuffer = new byte[1024];
    final ArrayList<Byte> readBuffer = new ArrayList<Byte>();
    // Skips to beginning of entryand then jumps 512 bytes to start of entry's data
    // TAR Entries are 512 byte entries
    try {
      fis = new FileInputStream(tarfile);
      fis.skip(entryStartOffset + 512);

      byte curByte = 0;
      while (bytesRead < entrySize) {

        curByte = (byte) fis.read();
        bytesRead++;

        if (curByte != 10 && curByte != 13) {
          while (curByte != 10 && curByte != 13) {
            readBuffer.add(curByte);
            curByte = (byte) fis.read();
            bytesRead++;
          }

          line = byteListToArray(readBuffer);
          readBuffer.clear();

          string = new String(line, StandardCharsets.UTF_8);
          System.out.println(lineCount);
          lineCount++;
          // System.out.println(Arrays.toString(line));
          // System.out.println(string);
          if (string.contains(";")) {
            final long startLine = System.currentTimeMillis();
            passCombo = string.split(";");
            if (passCombo.length == 2) {
              mongodb.insert(passCombo);
            }
            final long endLine = System.currentTimeMillis();
            System.out.println((endLine - startLine) + "ms insert");
          } else if (string.contains(":")) {
            final long startLine = System.currentTimeMillis();
            passCombo = string.split(":");
            if (passCombo.length == 2) {
              mongodb.insert(passCombo);
            }
            final long endLine = System.currentTimeMillis();
            System.out.println((endLine - startLine) + "ms insert");
          }
        }
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public static void readTarEntry(final File tarfile, final int entryStartOffset, final TarArchiveEntry tarEntry,final DatabaseHandlerMariaDB mariadb) {
    String fileName = tarfile.getName();
    String entryName = tarEntry.getName();

    FileInputStream fis;
    final long entrySize = tarEntry.getSize();
    long bytesRead = 0;
    String string = null;
    byte[] line = new byte[0];
    String[] passCombo = null;
    int lineCount = 0;

    // byte[] readbuffer = new byte[1024];
    final ArrayList<Byte> readBuffer = new ArrayList<Byte>();
    // Skips to beginning of entryand then jumps 512 bytes to start of entry's data
    // TAR Entries are 512 byte entries
    try {
      fis = new FileInputStream(tarfile);
      fis.skip(entryStartOffset + 512);

      byte curByte = 0;
      while (bytesRead < entrySize) {

        curByte = (byte) fis.read();
        bytesRead++;

        if (curByte != 10 && curByte != 13) {
          while (curByte != 10 && curByte != 13) {
            readBuffer.add(curByte);
            curByte = (byte) fis.read();
            bytesRead++;
          }

          line = byteListToArray(readBuffer);
          readBuffer.clear();

          string = new String(line, StandardCharsets.UTF_8);
          System.out.println(lineCount);
          // System.out.println(Arrays.toString(line));
          // System.out.println(string);
          if (string.contains(";")) {
            final long startLine = System.currentTimeMillis();
            passCombo = string.split(";");
            if (passCombo.length == 2) {
              mariadb.insert(passCombo);
            }
            final long endLine = System.currentTimeMillis();
            System.out.println((endLine - startLine) + "ms insert");
          } else if (string.contains(":")) {

            final long startLine = System.currentTimeMillis();
            passCombo = string.split(":");
            if (passCombo.length == 2) {
              mariadb.insert(passCombo);
            }

            final long endLine = System.currentTimeMillis();
            System.out.println((endLine - startLine) + "ms insert");
          }
          //Saves location of current file processing to file state.tmp

          Utility.setState(fileName,entryName,String.valueOf(lineCount));
          lineCount++;

        }
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  private static byte[] byteListToArray(final ArrayList<Byte> arrayList) {
    final byte[] a = new byte[arrayList.size()];
      for(int i = 0;i < arrayList.size();i++)
      {
        a[i] = arrayList.get(i);
      }
      return a;
    }
}
