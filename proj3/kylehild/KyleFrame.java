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

public class KyleFrame extends JFrame
{ 
  JPanel MainPanel;
  JPanel PlayersPanel; 
  JPanel WheelPanel;
  JPanel LettersPanel; 
  JPanel CenterPanel;    //all centralized panels for game
  JPanel BottomPanel; 
  JPanel ButtonPanel;
  JPanel PuzzlePanel;
  JPanel VowelsPanel;
  JPanel ConsonantsPanel;

  JButton vowelButton;
  JButton spinButton;
  JButton solveButton; //all buttons for game
  JButton[] vowels, consonants;

  ActionListener consListener, vowelListener;

  Random generator; //used for seed
  String fullPuzzle, dashedPuzzle; //for comparison
  int curPlayer, curSpace, numberConsGuessed, numberVowelsGuessed;
  String[] allPlayers;//player names
  int[] playerMoney;//player amount
  boolean[] guessedCons, guessedVowels; //guessed or not
  WheelSpace[] wheelSpaces;
  final int VOWELCOST = 250, NUM_WHEEL_SPACES = 24;

  public KyleFrame(String inTitle)
  {
    super(inTitle);

    curPlayer = 0;
    numberConsGuessed = 0;
    numberVowelsGuessed = 0;
    final JFrame mainFrame = this;  //initialize everything

    vowels = new JButton[5];
    consonants = new JButton[21];

    guessedCons = new boolean[21];
    guessedVowels = new boolean[5];

    /**************************** First Dialog Box ***************************/

    String seedQ = "Enter the random generator seed (int)";
    startDialog seedDialog;
    do{
      seedDialog = new startDialog(mainFrame, "Seed Input", seedQ);
      seedDialog.pack();
      seedDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      seedDialog.setVisible(true);
    }
    while(!isNumeric(seedDialog.getName()));//redo until got an integer

    /**************************** Second Dialog Box ***************************/

    String playersQ = "Enter space separated list of players";
    startDialog playersDialog;
    playersDialog = new startDialog(mainFrame, "Player Input", playersQ);
    playersDialog.pack();
    playersDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    playersDialog.setVisible(true);

    /**************************** Third Dialog Box ***************************/

    String puzQ = "Ask a non-player to enter a puzzle";
    startDialog puzDialog;
    puzDialog = new startDialog(mainFrame, "Puzzle Input", puzQ);
    puzDialog.pack();
    puzDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    puzDialog.setVisible(true);

    //get puzzle and convert to uppercase letters
    fullPuzzle = puzDialog.getName();
    fullPuzzle = fullPuzzle.toUpperCase();

    //start random number generator with seed given
    generator = new Random(Integer.parseInt(seedDialog.getName()));

    MainPanel = new JPanel(new BorderLayout());

    CenterPanel = new JPanel(new FlowLayout());
    BottomPanel = new JPanel(new BorderLayout());

    /****************************** Button Panel ******************************/

    ButtonPanel = new JPanel(new GridLayout(5,1));

    //button to click t buy a vowel
    vowelButton = new JButton("Buy A Vowel");
    vowelButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                  spinButton.setEnabled(false);
                  vowelButton.setEnabled(false); //must click a vowel
                  solveButton.setEnabled(false);
                  checkVowels(); //display which vowels are clickable
                }
              });
    vowelButton.setEnabled(false); //not enabled until you have enough money

    //button to click to spin the wheel
    spinButton = new JButton("Spin The Wheel");
    spinButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                  spinButton.setEnabled(false);
                  vowelButton.setEnabled(false); //must click a consonant
                  solveButton.setEnabled(false);
                  checkConsonants(); //display which consonants are clickable
                }
              });

    //button to click to solve th puzzle
    solveButton = new JButton("Solve The Puzzle");
    solveButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                  solveDialog solve; //open solve dialog box
                  solve = new solveDialog(mainFrame, "Solve Puzzle", 
                      playerMoney[curPlayer], fullPuzzle, allPlayers[curPlayer]);
                  solve.pack();
                  solve.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                  solve.setVisible(true);

                  //guessed correctly
                  if(solve.getGuess())
                    //exit
                    mainFrame.dispatchEvent(new WindowEvent(
                                  mainFrame, WindowEvent.WINDOW_CLOSING));
                  else{//guessed incorrectly
                    updatePlayer();
                    createPlayerPanel();
                  }
                }
              });

    ButtonPanel.add(vowelButton);
    ButtonPanel.add(new JLabel(""));
    ButtonPanel.add(spinButton);        //add buttons to panel
    ButtonPanel.add(new JLabel(""));
    ButtonPanel.add(solveButton);

    /**************************** Players Panel *****************************/

    String playerList = playersDialog.getName();
    playerList = playerList.trim().replaceAll(" +", " ");//get rid of multi spaces
    allPlayers = playerList.split(" "); //array of player names
    playerMoney = new int[allPlayers.length];
    for(int i = 0; i < allPlayers.length; i++){
      playerMoney[i] = 0; //initialize to zero for everyone
    }

    PlayersPanel = new JPanel(new GridLayout(1, allPlayers.length));
    createPlayerPanel(); //panel for player names and amount

    /**************************** Wheel Panel ******************************/

    parseWheel(); //get all wheelspaces and put them in global array
    WheelPanel = new JPanel(new FlowLayout());
    //show first image when game starts
    JLabel wIcon = new JLabel(wheelSpaces[0].image); 
    WheelPanel.add(wIcon);
    
    /**************************** Letter Panel ******************************/

    LettersPanel = new JPanel(new BorderLayout());
    VowelsPanel = new JPanel(new GridLayout(3,2));
    addVowels(); //add vowels as not enabled buttons
    ConsonantsPanel = new JPanel(new GridLayout(3,5));
    addConsonants(); //add consonants as not enabled 

    /**************************** Puzzle Panel ******************************/

    PuzzlePanel = new JPanel(new FlowLayout());

    //replace all alphabetic characters with a '-'
    dashedPuzzle = fullPuzzle.replaceAll("[a-zA-Z]", "-");
    
    //add spaces between every character in bith strings
    dashedPuzzle = dashedPuzzle.replace("", " ").trim();
    fullPuzzle = fullPuzzle.replace("", " ").trim();
    
    PuzzlePanel.add(new JLabel(dashedPuzzle));
    
    //layout of full game
    setLayout(new FlowLayout());

    /**************************** Main Panel ******************************/

    MainPanel.add(PlayersPanel, BorderLayout.NORTH);

    CenterPanel.add(ButtonPanel);
    CenterPanel.add(WheelPanel);
    MainPanel.add(CenterPanel, BorderLayout.CENTER);  //make it look like spec

    LettersPanel.add(VowelsPanel, BorderLayout.WEST);
    LettersPanel.add(ConsonantsPanel, BorderLayout.EAST);
    BottomPanel.add(LettersPanel, BorderLayout.NORTH);
    BottomPanel.add(PuzzlePanel, BorderLayout.SOUTH);
    MainPanel.add(BottomPanel, BorderLayout.SOUTH);

    add(MainPanel);
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

  private void updatePlayer(){
    //next player
    curPlayer++;
    if(curPlayer >= allPlayers.length)
      curPlayer = 0;//wrap around
  }

  private void createPlayerPanel(){
    PlayersPanel.removeAll(); //remover anything in player panel

    //color for player borders
    Border redline = BorderFactory.createLineBorder(Color.red);
    Border blackline = BorderFactory.createLineBorder(Color.black);

    //different panel for each player
    for(int i = 0; i < allPlayers.length; i++){
      TitledBorder nameBorder;
      JPanel NamePanel = new JPanel(new FlowLayout());

      //curPlayer has red border, everyone else has black
      if(i == curPlayer)
        nameBorder = BorderFactory.createTitledBorder(redline, allPlayers[i]);
      else
        nameBorder = BorderFactory.createTitledBorder(blackline, allPlayers[i]);

      //set border and amount for each player
      NamePanel.setBorder(nameBorder);
      NamePanel.add(new JLabel(Integer.toString(playerMoney[i])));
      PlayersPanel.add(NamePanel);
    }

    //decide if vowel button is clickable for this player based on amount
    //and number of vowels clicked by all players
    if(playerMoney[curPlayer] >= VOWELCOST && numberVowelsGuessed != 5)
      vowelButton.setEnabled(true);
    else
      vowelButton.setEnabled(false);

    MainPanel.revalidate(); //update main panel
  }

  private void addVowels(){
    TitledBorder vowelTitle;
    vowelListener = new VowelsListener();

    vowels[0] = new JButton("A");
    vowels[1] = new JButton("E");
    vowels[2] = new JButton("I");   //new button for each vowel
    vowels[3] = new JButton("O");
    vowels[4] = new JButton("U");


    vowelTitle = BorderFactory.createTitledBorder("Vowels");
    VowelsPanel.setBorder(vowelTitle);
    
    for(int i = 0; i < 5; i++){
      VowelsPanel.add(vowels[i]); //add button to panel
      vowels[i].setEnabled(false); //enabled false until the try to buy
      vowels[i].addActionListener(vowelListener); //listener for when they press
    }
  }

  private void addConsonants(){
    TitledBorder consTitle;

    char curChar = 'B'; //starting character
    String button;
    consListener = new ConsonantsListener(); //listener for all consonants

    //new button for all consonants
    for(int i = 0; i < 21; i++, curChar++){
      //skip vowels but still increment char
      if(curChar == 'E' || curChar == 'I' || curChar == 'O' || curChar == 'U')
        curChar++;
      button = String.valueOf(curChar);
      consonants[i] = new JButton(button);
      consonants[i].addActionListener(consListener); //add listener
    }

    consTitle = BorderFactory.createTitledBorder("Consonants");
    ConsonantsPanel.setBorder(consTitle);
    
    for(int i = 0; i < 21; i++){
      ConsonantsPanel.add(consonants[i]); //add to panel
      consonants[i].setEnabled(false); //set not enabled till they spin
    }
  }

  private void checkConsonants(){
    //get new space based on seed
    curSpace = generator.nextInt(NUM_WHEEL_SPACES);

    //place new wheel space on board
    WheelPanel.removeAll();
    JLabel wIcon = new JLabel(wheelSpaces[curSpace].image);
    WheelPanel.add(wIcon);

    //bankrupt
    if(wheelSpaces[curSpace].money == -1){
      playerMoney[curPlayer] = 0; //lose all money

      updatePlayer(); //get next player
      
      if(numberConsGuessed != 21) //
        spinButton.setEnabled(true);
      solveButton.setEnabled(true);

      createPlayerPanel(); //update amounts and player
      return;
    }
    else if(wheelSpaces[curSpace].money == -2){
      updatePlayer();//get next player

      if(numberConsGuessed != 21)
        spinButton.setEnabled(true);
      solveButton.setEnabled(true);

      createPlayerPanel(); //update player panel
      return;
    }

    for(int i = 0; i < 21; i++){
      if(!guessedCons[i]){ //not been guessed before set enabled
        consonants[i].setEnabled(true);
      }

    }
    MainPanel.revalidate(); //refresh mainPanel
  }

  private void checkVowels(){
    for(int i = 0; i < 5; i++){
      if(!guessedVowels[i]){ //not been guessed before set enabled
        vowels[i].setEnabled(true);
      }
    }
  }

  private int listenerHelper(ActionEvent e){
      //get button that was pressed and its char
      JButton button = (JButton) e.getSource();
      String temp = button.getText();
      char letter = temp.charAt(0);

      //if not all consonants have been pressed
      if(numberConsGuessed != 21)
        spinButton.setEnabled(true);
      solveButton.setEnabled(true);

      int i, j, num = 0;

      //can't find letter in string
      if((i = fullPuzzle.indexOf(letter, 0)) == -1){
        updatePlayer();      //get next player
        createPlayerPanel();
        return -1;
      }

      //get number of instances and place in dashed string
      for(i = -1; (i = fullPuzzle.indexOf(letter, i + 1)) != -1; ){
        num++;
        dashedPuzzle = dashedPuzzle.substring(0, i) + 
                        letter + dashedPuzzle.substring(i+1);
      }

      return num;
  }

  public class ConsonantsListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      for(int i = 0; i < 21; i++){ //disable all buttons after one is pressed
        consonants[i].setEnabled(false);
      }

      //get button that was pressed and its char
      JButton button = (JButton) e.getSource();
      
      //set that this button has been pressed and increment number pressed
      for(int i = 0; i < consonants.length; i++){
        if(button == consonants[i]){
          guessedCons[i] = true;
          numberConsGuessed++;
          break;
        }
      }

      int num = listenerHelper(e); //run helper function
      if(num == -1) return;

      //update amount for curplayer based on how many times the letter existed
      playerMoney[curPlayer] += num * wheelSpaces[curSpace].money;
      createPlayerPanel();

      //update puzzle at bottom of game to reflect guess
      PuzzlePanel.removeAll();
      PuzzlePanel.add(new JLabel(dashedPuzzle));
      MainPanel.revalidate(); //refresh main panel
    }
  }

  public class VowelsListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      for(int i = 0; i < 5; i++){ //disable all vowels when one is pressed
        vowels[i].setEnabled(false);
      }

      //get button that was pressed
      JButton button = (JButton) e.getSource();

      //set that this button has been pressed and increment number pressed
      for(int i = 0; i < vowels.length; i++){
        if(button == vowels[i]){
          guessedVowels[i] = true;
          numberVowelsGuessed++;
          break;
        }
      }

      int num = listenerHelper(e); //run helper function
      if(num == -1) return;

      //subtract cost of buying a vowel
      playerMoney[curPlayer] -= VOWELCOST;
      createPlayerPanel();

      //update puzzle to reflect guess
      PuzzlePanel.removeAll();
      PuzzlePanel.add(new JLabel(dashedPuzzle));
      MainPanel.revalidate();
    }
  }

  private void parseWheel(){
    File [] fileList;
    File myDir = null;
    int i;

    //Allocate array for number of spaces, which is set to a constant
    //for now as opposed to being able to change run-to-run
    wheelSpaces = new WheelSpace[NUM_WHEEL_SPACES];

    //Get a File object for the directory containing the images
    try{
      myDir = new File(getClass().getClassLoader().getResource(
                                      "eecs285/proj3/kylehild/images").toURI());
    } catch (URISyntaxException uriExcep){
      System.out.println("Caught a URI syntax exception");
      System.exit(4); //Just bail for simplicity in this project
    }

    //Loop from 1 to the number of spaces expected, so we can look
    //for files named <spaceNumber>_<value>.jpg.  Note: Space numbers
    //in image filenames are 1-based, NOT 0-based.
    for (i = 1; i <= NUM_WHEEL_SPACES; i++){
      //Get a listing of files named appropriately for an image
      //for wheel space #i.  There should only be one, and this
      //will be checked below.
      fileList = myDir.listFiles(new WheelSpaceImageFilter(i));

      if (fileList.length == 1)
      {
        //System.out.println("Space: " + i + " img: " + fileList[0] +
        //        " val: " + WheelSpaceImageFilter.getSpaceValue(fileList[0]));
        //Index starts at 0, space numbers start at 1 -- hence the "- 1"
        wheelSpaces[i - 1] = new WheelSpace(
                              WheelSpaceImageFilter.getSpaceValue(fileList[0]),
                              new ImageIcon(fileList[0].toString()));
      }
      else
      {
        System.out.println("ERROR: Invalid number of images for space: " + i);
        System.out.println("       Expected 1, but found " + fileList.length);
      }
    }
  }
}