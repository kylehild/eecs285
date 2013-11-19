package eecs285.proj3.kylehild;
import static java.lang.System.out;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class solveDialog extends JDialog
{
  JTextField textField;
  JButton okButton;
  JPanel LabelPanel;
  JPanel TextFieldPanel;
  JPanel ButtonPanel;
  boolean guess; //guess correctly or not

  public solveDialog(JFrame mainFrame, String title, final int money, 
                                  final String puzzle, final String player)
  {
    super(mainFrame, title, true);
    
    //declare panels used in dialog
    LabelPanel = new JPanel(new BorderLayout());
    TextFieldPanel = new JPanel(new BorderLayout());
    ButtonPanel = new JPanel(new FlowLayout());
    setLayout(new BorderLayout());

    //add question
    LabelPanel.add(new JLabel("Enter complete puzzle exactly as displayed"));
    
    //text field 40 characters wide
    textField = new JTextField(40);
    TextFieldPanel.add(textField);

    //button with ok on it
    okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                  //if text box is not empty
                  if(!textField.getText().equals("")){
                    //convert to uppercase for comparing with full puzzle string
                    String answer = textField.getText().toUpperCase();
                    answer = answer.replace("", " ").trim(); //format like full

                    //not correct
                    if(!answer.equals(puzzle)){
                      setVisible(false);

                      //show wrong answer dialog box
                      String info = "Guess by " + player + " was incorrect!";
                      JOptionPane.showMessageDialog(null,
                                      info,
                                      "Wrong Answer",
                                      JOptionPane.ERROR_MESSAGE);
                      guess = false; //did not guess right
                    }
                    else{
                      setVisible(false);

                      //show winning dialog box
                      String info = player + " wins $" + money;
                      JOptionPane.showMessageDialog(null,
                                      info,
                                      "Game Over",
                                      JOptionPane.INFORMATION_MESSAGE);
                      guess = true; //did guess right
                    }
                  }
                }
              });
    ButtonPanel.add(okButton);


    //place in positions in box to match spec
    add(LabelPanel, BorderLayout.NORTH);
    add(TextFieldPanel, BorderLayout.CENTER);
    add(ButtonPanel, BorderLayout.SOUTH);
  }
  
  public boolean getGuess()
  {
    return (guess);
  }
}