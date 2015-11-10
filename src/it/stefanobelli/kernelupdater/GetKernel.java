/**
 * @author Stefano Belli
 * @version 0.7
 */
package it.stefanobelli.kernelupdater;

import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import java.io.IOException;

class GetKernel //implements Runnable
{

  //Class constructor
  GetKernel(String url, String outName)
  {
    this.url = url;
    this.outName = outName;
    this.userHomeDirectory = System.getProperty("user.home");
    this.outPath = userHomeDirectory+"/"+outName;
    try{
      connectServer();
    }
    catch(Exception excp)
    {

    }
  }

  /**/
  private final String outName;
  private final String url;
  private long kernelSize;
  private String lastModified;
  private String fileType;
  private URL kernelUrl;
  private FileOutputStream kernelOut;
  private ReadableByteChannel streamChannel;
  private URLConnection urlconn;
  private final String userHomeDirectory;
  private final String outPath;
  /**/

  protected void connectServer() throws Exception
  {
    kernelUrl = new URL(url);
    urlconn = kernelUrl.openConnection();
    urlconn.connect();
    fileType = urlconn.getContentType();
    kernelSize = urlconn.getContentLengthLong();
    lastModified = urlconn.getHeaderField("Last-Modified");
  }

  protected void getKernel() throws IOException
  {
    streamChannel = Channels.newChannel(kernelUrl.openStream());
    System.out.println(kernelUrl.openStream());
    kernelOut = new FileOutputStream(outPath);
    kernelOut.getChannel().transferFrom(streamChannel,0,Long.MAX_VALUE);
  }

  //Getters

  /** @return long precision kernel size */
  protected long getKernelSize()
  {
    return this.kernelSize;
  }

  /** @return String object filename */
  protected String getKernelOutputFilename()
  {
    return this.outPath;
  }

  /** @return String object url */
  protected String getUrl()
  {
    return this.url;
  }

  /** @return String object Content-type */
  protected String getKernelType()
  {
    return this.fileType;
  }

  /** @return String object from header field Last-Modified */
  protected String getKernelLastMod()
  {
    return this.lastModified;
  }
}
