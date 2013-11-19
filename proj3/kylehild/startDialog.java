package eecs285.proj3.kylehild;
import static java.lang.System.out;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class startDialog extends JDialog
{
  JTextField textField;
  JButton okButton;
  JPanel LabelPanel;
  JPanel TextFieldPanel;
  JPanel ButtonPanel;

  public startDialog(JFrame mainFrame, String title, String question)
  {
    super(mainFrame, title, true);

    //declare panels used in dialog
    LabelPanel = new JPanel(new BorderLayout());
    TextFieldPanel = new JPanel(new BorderLayout());
    ButtonPanel = new JPanel(new FlowLayout());
    setLayout(new BorderLayout());

    //add question
    LabelPanel.add(new JLabel(question));
    
    //text field 40 characters wide
    textField = new JTextField(40);
    TextFieldPanel.add(textField);

    //button with ok on it
    okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener()
                                    {
                                      public void actionPerformed(ActionEvent e)
                                      {
                                        //if there is something in the text box
                                        if(!textField.getText().equals(""))
                                          setVisible(false);
                                      }
                                    });
    ButtonPanel.add(okButton);

    //place in positions in box to match spec
    add(LabelPanel, BorderLayout.NORTH);
    add(TextFieldPanel, BorderLayout.CENTER);
    add(ButtonPanel, BorderLayout.SOUTH);
  }
  
  public String getName()
  {
    return (textField.getText());
  }
}