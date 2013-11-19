package eecs285.proj3.kylehild;
import static java.lang.System.out;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.applet.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;
import java.util.*;

//Helper inner class to filter images used for wheel spaces,
//based on specifically expected filename format.
public class WheelSpaceImageFilter implements FileFilter
{
  String prefix;  //The prefix of the filename we're looking
                  //for - what comes before the first underscore

  WheelSpaceImageFilter(int inPref)
  {
    //Sets the prefix member to string version of space number
    prefix = new Integer(inPref).toString();
  }

  //Test whether the file provided should be accepted by our
  //file filter. In the FileFilter interface.
  public boolean accept(File fname)
  {
    boolean isAccepted = false;

    //Accepted if matched "<prefix>_<...>.jpg" where IMAGE_EXTENSION
    //is assumed to be "jpg" for this example
    if (fname.getName().startsWith(prefix + "_") &&
        fname.getName().endsWith(".jpg"))
    {
      isAccepted = true;
    }

    return (isAccepted);
  }

  //Parses the provided filename to determine the dollar value
  //associated with this wheel space image's filename.
  public static int getSpaceValue(File fname)
  {
    String filename = fname.getName();
    String[] index = filename.split("_"); //gets index
    String[] value = index[1].split("\\."); //gets value up until .jpg

    //check if money associated is nummeric
    boolean check = isNumeric(value[0]);

    if(check)
      return Integer.parseInt(value[0]);
    else if(value[0].equals("bankrupt"))
      return -1;//bankrupt
    else
      return -2;//lose a turn
  }

  public static boolean isNumeric(String str)  {  
    try{  
      Integer.parseInt(str);  
    }  
    catch(NumberFormatException nfe){  
      return false; //not an integer 
    }  
    return true;  
  }
}