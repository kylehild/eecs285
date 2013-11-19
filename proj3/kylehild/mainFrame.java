package eecs285.proj3.kylehild;
import static java.lang.System.out;
import javax.swing.*;

public class mainFrame extends JFrame
{ 
  public mainFrame(String inTitle)
  {
    super(inTitle);
    
    String seedQ = "Enter the random generator seed (int)";
    startDialog seedDialog = new startDialog(this, "Seed Input", seedQ);

  }
}