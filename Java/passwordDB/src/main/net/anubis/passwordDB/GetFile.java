package net.anubis.passwordDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.ArrayList;

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
    ArrayList<TarArchiveEntry> tarEntryArray = new ArrayList<TarArchiveEntry>();
    TarArchiveInputStream currentStream = tis;
    TarArchiveEntry currentEntry = null;
    currentStream.getNextTarEntry();
    while ((currentEntry = currentStream.getCurrentEntry()) != null)
    {

      tarEntryArray.add(currentEntry);
      currentStream.getNextTarEntry();
    }
    currentStream.close();

    TarArchiveEntry[] tarEntryArray_final = new TarArchiveEntry[tarEntryArray.size()];
    for (int counter = 0; counter < tarEntryArray.size(); counter++)
    {
      	tarEntryArray_final[counter] = tarEntryArray.get(counter);
    }
    return (TarArchiveEntry[])tarEntryArray_final;
    }
    


  }
