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

public class WheelSpace
{
  int money; //money associated with space
  ImageIcon image; //image associated with space

  WheelSpace(int inMoney, ImageIcon inImage){
    money = inMoney;
    image = inImage;
  }
}