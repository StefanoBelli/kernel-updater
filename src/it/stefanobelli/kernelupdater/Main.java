package it.stefanobelli.kernelupdater;

import it.stefanobelli.kernelupdater.GetKernel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.Scanner;
import java.net.UnknownHostException;

public class Main
{
  static final int ARRAY_OUTBOUNDS = 1;
  static final int HOST_NOTREACHABLE = 2;
  static String url;
  static String filename;
  static int series;
  static String version;

  public static void main(String[] args)
  {
    GetKernel getk;
    Scanner getVersion;

    series = 0;
    version = "";
    getVersion = new Scanner(System.in);
    System.out.print("--> Kernel version to fetch: ");
    version = getVersion.next();

    checkVersion();
    url = "https://www.kernel.org/pub/linux/kernel/v"+series+".x/"+"linux-"+version+".tar.xz";
    filename = "linux-"+version+".tar.xz";

    getk = new GetKernel(url,filename);
    System.out.println("File output: "+getk.getKernelOutputFilename());
    System.out.println("Downloading from: "+getk.getUrl());
    System.out.println("Kernel size: "+getk.getKernelSize()+" Bytes");
    System.out.println("Content type: "+getk.getKernelType());
    System.out.println("Last modified: "+getk.getKernelLastMod());
    try
    {
      getk.getKernel();
    }
    catch(UnknownHostException unknownHostException)
    {
      System.err.println("-!-> Host is not reachable... Check connection!!");
      System.exit(HOST_NOTREACHABLE);
    }
    catch(IOException ioe)
    {
      System.err.println("-!-> I/O Exception raised!!!\nPrinting stack trace");
      ioe.printStackTrace();
    }
    catch(Exception generalException)
    {
      System.err.println("-!-> General Exception raised!!!\nPrinting stack trace");
      generalException.printStackTrace();
    }
  }

  public static void checkVersion()
  {
    String regFour = "^4";
    String regThree = "^3";
    String regTwo = "^2";

    Pattern patternFour = Pattern.compile(regFour);
    Pattern patternThree = Pattern.compile(regThree);
    Pattern patternTwo = Pattern.compile(regTwo);

    Matcher matcherFour = patternFour.matcher(version);
    Matcher matcherThree = patternThree.matcher(version);
    Matcher matcherTwo = patternTwo.matcher(version);

    if(matcherFour.find())
      series = 4;
    else if(matcherThree.find())
      series = 3;
    else if(matcherTwo.find())
      series = 2;
    else
      System.err.println("-!-> Kernel version not valid!!");
  }
}
