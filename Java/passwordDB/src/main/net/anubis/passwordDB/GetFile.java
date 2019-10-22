package net.anubis.passwordDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class GetFile
{
  public File deCompressGZipFile(File gZippedFile, File tarFile) throws IOException{
      FileInputStream fis = new FileInputStream(gZippedFile);
      GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);

      FileOutputStream fos = new FileOutputStream(tarFile);
      byte[] buffer = new byte[1024];
      int len;
      while((len = gZIPInputStream.read(buffer)) > 0){
          fos.write(buffer, 0, len);
      }

      fos.close();
      gZIPInputStream.close();
      return tarFile;

  }
  public TarArchiveInputStream getTarArchiveStream(File tarFile)throws IOException
  {
    FileInputStream fis = new FileInputStream(tarFile);
    TarArchiveInputStream tis = new TarArchiveInputStream(fis);
    // TarArchiveEntry currentEntry = null;
    // tis.getNextTarEntry();
    // while ((currentEntry = tis.getCurrentEntry()) != null)
    // {
    //   System.out.println(currentEntry.getName());
    //   tis.getNextTarEntry();
    // }
    return tis;
  }

  public TarArchiveEntry[] getEntries(TarArchiveInputStream tis) throws IOException
  {
    TarArchiveInputStream currentStream = tis;
    TarArchiveEntry currentEntry = null;
    int streamSize = -1;
    currentStream.getNextTarEntry();
    while ((currentEntry = currentStream.getCurrentEntry()) != null)
    {

      streamSize++;
      currentStream.getNextTarEntry();
    }
    currentStream.reset();
    TarArchiveEntry[] tarEntryArray = new TarArchiveEntry[streamSize];
    //currentStream = tis;
    // currentEntry = currentStream.getCurrentEntry();
    // currentStream.getNextTarEntry();
    /*
    HOW to bypass TarArchiveInputStream lack of reset
    Change ARRAY to array list to dynamically add Entries thenconvert to ARRAY
    */
    for(int i = 0 ; i < streamSize;i++)
    {
      currentEntry = currentStream.getCurrentEntry();
      System.out.println(currentEntry.getName());
      currentStream.getNextTarEntry();
      if (currentEntry != null)
      {
        tarEntryArray[i] = currentEntry;
      }

    }
    //System.out.println(tarEntryArray.toString());
    return tarEntryArray;

  }

}
