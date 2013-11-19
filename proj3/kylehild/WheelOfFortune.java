package eecs285.proj3.kylehild;
import static java.lang.System.out;
import java.io.*;
import java.net.*;
import javax.swing.*;

/** This is an application class that contains a main function,
 *  which will test the interface to the beginnings of a chess
 *  game, as specified by EECS285 programming project #2.
 *  The program takes no interactive or file inputs, and all
 *  output is to the console.
 *  @author  Andrew M. Morgan
 */
public class WheelOfFortune
{
  /** This method is called automatically when the WheelOfFortune class
   *  is interpreted by the JVM.
   */
  public static void main(String [] args)
  {

    KyleFrame main;
    main = new KyleFrame("Wheel Of Fortune"); //call mainframe
    main.pack();
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setVisible(true);
  }
}