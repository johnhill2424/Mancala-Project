import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/** 

* MancalaGame recreates the classic board game, Mancala, and is easy to use and beautiful to look at. It is 
* a fantastic piece of software, packaged up nicely with America's favorite language, Java, along with 
* the trustworthy IDE, JGrasp. 
*  
* @author John Hill and Jack Old 
* @version 1.0 
* @since 2017-10-16

*/

public class MancalaGame extends JFrame implements ActionListener{
   
   private JButton jbutton;
   private JMenuBar jmb;
   private JMenu jm;
   private JMenuItem jmiAbout,jmiRestart,jmiExit,jmiSave, jmiLoadGame;
   private Player1 p1;
   private Player2 p2;
   private boolean playerOneTurn = true; //if true, its player 1's turn, if false its player 2's turn
   private boolean endOfGame = false; 
   
   private ArrayList<JButton> jbList = new ArrayList<JButton>();
   private ArrayList<ImageIcon> imgList = new ArrayList<ImageIcon>();
   
   private JLabel p1Score = new JLabel("0");
   private JLabel p2Score = new JLabel("0");

   /** 
   
   * Main class, creates a MancalaGame object 
   *   
   * @param args Arguments  
   * 
   */

   public static void main(String[] args){
      new MancalaGame();
   }

   /** 
   *
   * Constructor for MancalaGame class
   * Creates the GUI using JFrame, JPanels, JMenus, and JButtons 
   * Adds actionlisteners for all the buttons and menu items 
   *
   */

   public MancalaGame(){


      //Creates the buttons and initializes them to their values
      for(int i = 0; i <= 13; i++){
         //This part creates the Mancala's on the sides 
         if(i == 6 || i == 13){
            JButton jbutton = new JButton("0");
            jbutton.setFont(new Font("Arial", Font.PLAIN, 40));
            jbutton.setEnabled(false);
            jbutton.setName(""+i);
            jbList.add(jbutton);
         }
         //This part creates all the other pits 
         else{
            JButton jbutton = new JButton("4");
            jbutton.setFont(new Font("Arial", Font.PLAIN, 40));
            jbutton.setName(""+i);
            jbList.add(jbutton);
         }
         
      } //End of for loop 

      //Adds the images to the imgList arraylist
      for(int i = 0; i <= 10; i++){
         try {
            ImageIcon img = new ImageIcon("manc_marbles/marble"+i+".png");
            imgList.add(img); 
         }
         catch(Exception e){
            System.out.println(e);
         }
      }
     
      //the marbleAmount method updates the images to match the values in the buttons 
      marbleAmount();
 
      //creates a panel for the top row of buttons 
		JPanel topRow = new JPanel();
		for(int i=12; i>=7; i--){ 
         topRow.add(jbList.get(i));
      }
		
      //creates a panel for the bottom row of buttons 
		JPanel bottomRow = new JPanel();
		for(int i=0; i<=5; i++){
         bottomRow.add(jbList.get(i));
      }

      //creates a panel to hold both the top and bottom rows 
		JPanel bothRows = new JPanel();
		bothRows.setLayout( new GridLayout(2, 1) );
		bothRows.add(topRow);
		bothRows.add(bottomRow);

		//creates a paenl to combine both rows with the mancalas on both sides 
		JPanel jpMain = new JPanel();	
      jpMain.setLayout(new BorderLayout());
      jpMain.add(bothRows, BorderLayout.CENTER);
      jpMain.add(jbList.get(13), BorderLayout.WEST);
      jpMain.add(jbList.get(6), BorderLayout.EAST);
      
      //makes the panel to hold the menubar and makes it float to the left 
      JPanel jmbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      
      //MenuBar
      jmb = new JMenuBar();
      
      //JMenu
      jm = new JMenu("File");
      jm.setFont(new Font("Arial", Font.PLAIN, 20));
      jmb.add(jm);
      
      //JMenuItems
      jmiAbout = new JMenuItem("About");
      jmiRestart = new JMenuItem("Restart");
      jmiExit = new JMenuItem("Exit");
      jmiSave = new JMenuItem("Save");
      jmiLoadGame = new JMenuItem("Load Game");
      jmiAbout = new JMenuItem("About");

      //Add JMenuItems to the JMenu
      jm.add(jmiAbout);
      jm.add(jmiRestart);
      jm.add(jmiExit);
      jm.add(jmiSave);
      jm.add(jmiLoadGame);
      jm.add(jmiAbout);
      
      //add menubar to menupanel
      jmbPanel.add(jmb);
      
      //Creates actionListener for restart menu item and calls resetGame method 
      jmiRestart.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            resetGame();
            p1Score.setText("0");
            p2Score.setText("0");
            
         }
      });
      
      //Creates actionListener for exit menu item
      jmiExit.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            System.exit(0);
         }
      });
      
      
      //Creates actionListener for exit menu item
      jmiAbout.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            String aboutPage = String.format("Welcome! If you clicked on this about page, chances are %n"+
                                             "looking to understand how to play the game, Mancala.%n"+

                                             "It's very simple. The entire goal of the game is to%n"+
                                             "get more marbles into your mancala (the side pit).%n"+
                                             "This is done by clicking on the marbles on your side%n"+
                                             "of the board. The marbles move in a counter-clockwise%n"+
                                             "fashion, and drop a marble in each pit it crosses%n"+
                                             "(this also includes your mancala).%n%n"+
                                             
                                             "There are some rules that also allow you to get%n"+
                                             "more marbles into your mancala.%n%n"+
                                             
                                             "1. If the last marble placed down lands into an%n"+
                                             "empty pit on your side, and the pit across%n"+
                                             "from it has marbles in it, all of the marbles on%n"+
                                             "their side and your marble on your side go into your%n"+
                                             "mancala.%n%n"+
                                             
                                             "2. If the last marble placed down lands in your manacla,%n"+
                                             "you get to go again,%n%n"+
                                             
                                             "Ending of game,%n"+
                                             "-The game ends when all six spaces on one side of the Mancala,%n"+
                                             "board are empty,%n"+
                                             "-The player who still has pieces on his side of the board when %n"+
                                             "the game ends captures all of those pieces,%n"+
                                             "-Count all the pieces in each store, the winner is the,%n"+
                                             "player with the most pieces,%n"
                                             );
         
            JOptionPane.showMessageDialog(null, aboutPage);
         }
      });
      
      //Creates actionListener for save menu item and calls the save method 
      jmiSave.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            save();
         }
      });
      
      //Creates actionListener for load menu item and calls the load method 
      jmiLoadGame.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            load();
         }
      });
      

      //Changes the font and color of p1Score and p2Score JLabel and initializes them
      p1Score.setFont(new Font("Arial", Font.PLAIN, 30));
      p1Score.setForeground(Color.RED);
      p2Score.setFont(new Font("Arial", Font.PLAIN, 30));
      p2Score.setForeground(Color.BLUE);
      
      JLabel jlP1 = new JLabel("Scores| Player 1: ");
      jlP1.setFont(new Font("Arial", Font.PLAIN, 30));
      
      JLabel jlP2 = new JLabel(" Player 2: ");
      jlP2.setFont(new Font("Arial", Font.PLAIN, 30));
      
      //adds labels to the menubar panel 
      jmbPanel.add(jlP1);
      jmbPanel.add(p1Score);
      jmbPanel.add(jlP2);
      jmbPanel.add(p2Score);

      //add menupanel to main panel
      jpMain.add(jmbPanel,BorderLayout.NORTH);

      //add to frame
      add(jpMain);
   
      //Add actionListeners for all all buttons
      for(int i=0;i<14;i++){      
         jbList.get(i).addActionListener(this);
      }
   
      //Disable player 2's buttons to star, player1 will start 
      for(int i=7; i<=12;i++){
         jbList.get(i).setEnabled(false);
      }

      //Windows settings and visibility
      pack();
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      
      //creates and initializes player1 and player2 objects 
      initializePlayers();
      
   }//end of constructor 
   
   
   /** 
   * Gets the name of the button that was clicked and passes it to the takeTurn method
   * marbleAmount() assigns the images to the buttons 
   * @param ae ActionEvent
   */
   
   public void actionPerformed(ActionEvent ae){
      String name = ((JButton)ae.getSource()).getName();
      takeTurn(name);
      marbleAmount();
   }
   
   
   /** 
   * Creates player1 and player2 objects 
   */
   
   public void initializePlayers(){
      p1 = new Player1();
      p2 = new Player2();
   }
   
   
   /** 
   * Resets the game back to start conditions, sets all the pits to 4,
   * and all the mancalas to 0 
   */
   
   public void resetGame(){
      
      for(int i=0;i<jbList.size();i++){ 
         if(i == 6 || i == 13){
            jbList.get(i).setText("0"); 
         }
         else{ 
            jbList.get(i).setEnabled(true);
            jbList.get(i).setText("4");
         }
      }
      for(int i=7;i<=12;i++){
         jbList.get(i).setEnabled(false);
      }
      marbleAmount();
      playerOneTurn = true;
      endOfGame = false;

   }
   
   /** 
   * Creates a file that saves the current game conditions 
   */
   
   public void save(){
   
      try{
         File game1 = new File("savedGame.txt");
         PrintWriter pw = new PrintWriter(game1);
                        
         for(int i=0; i<jbList.size(); i++){
            String sNum = jbList.get(i).getText();
            sNum = String.format("%s ",sNum);
            pw.write(sNum); 
         }//end of for loop 
         
         
         String wScores = String.format("%s %s ",p1Score.getText(),p2Score.getText());
         
         pw.write(wScores);
         
         if (playerOneTurn == true){
            pw.write("true");
         }
         else if(playerOneTurn == false){
            pw.write("false");
         }
         
         pw.close();
      
      } // end of try 
      
      catch (IOException io){
         io.printStackTrace();
      } // end of catch 

   
   } //end of save method 
   
   
   /** 
   * Loads in the saved file to resume play
   */
   
   public void load(){
   
      try{
         BufferedReader br = new BufferedReader(new FileReader("savedGame.txt"));
         String output = br.readLine();
         
         String[] split = output.split(" ");
         for (int i = 0; i < jbList.size(); i++){
            jbList.get(i).setText(split[i]);
         }
         
         p1Score.setText(split[13]);
         p2Score.setText(split[14]);
         
         marbleAmount();
         
         if(split[16].equals("false")){
            playerOneTurn = false;
            p2.enable();
         
         }
         else if(split[16].equals("true")){
            playerOneTurn = true;
            p1.enable();
         }
         
         br.close();
      
      } // end of try 
      
      catch (IOException io){
         io.printStackTrace();
      } // end of catch 

   } //end of load method 
   
   
   /** 
   * marbleAmount() takes the current amount of marbles in pocket, and matches the corresponding picture
   */
   
   public void marbleAmount(){
      
      for(int i=0; i<jbList.size(); i++){
         String sNum = jbList.get(i).getText();
         int num = Integer.parseInt(sNum);
         
         if(num > 15){
            jbList.get(i).setIcon(imgList.get(10));
            jbList.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
            jbList.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
         }
         else if(num > 9){
            jbList.get(i).setIcon(imgList.get(9));
            jbList.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
            jbList.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
         }
         
         else{
            jbList.get(i).setIcon(imgList.get(num));
            jbList.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
            jbList.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
         }
         
      }
      
   }

   /** 
   * This is the main driving method for the program 
   * First it makes the string name into an integer
   * Then it gets the current value of the button that was clicked
   * Then it goes through a for loop to add the marbles to the pits and mancalas
   * Then it checks to see if the last marble landed in an empty pit 
   * Then it checks to see if the last marble landed in the players own Mancala, and if so the player goes again
   *   
   * @param name Name of the button that was clicked 
   * 
   */

   public void takeTurn(String name){
      
      int clickedName = Integer.parseInt(name); //convert the String name to number, get the button number that was clicked
      int clickedNameStored = clickedName;
      
      String sCurrentValue = jbList.get(clickedName).getText(); //get the current value of the button that was clicked
      int currentValue = Integer.parseInt( sCurrentValue); // make the current value of the button into a number

      //Each individual turn, adds one to subsequent holes, from 0 to number of marbles in the current hole
      for (int i = 1; i <= currentValue; i++){
        
         if (clickedName+i > 13){
            clickedName = 0;
            currentValue = currentValue - i;
            i = 0;
         }  
         
         String sNextButtonValue = jbList.get(clickedName+i).getText(); //get the current value of the next button in line
         int nextButtonValue = Integer.parseInt( sNextButtonValue ); // make the current value of the button into a number
         
         String sNextValue = String.format( "%d", nextButtonValue+1); //add one to the current value of the current button
         jbList.get(clickedName+i).setText(sNextValue); //Update the button to hold the current value
     
      } // end of the loop
      
      jbList.get(clickedNameStored).setText("0"); //Set the text of the button that was clicked to 0 
      
      //clickedName + currentValue represents the last marble of a turn
      //This part checks to see if a marble lands on an empty pit, if so it calls the across method 
      if (playerOneTurn == true){
         if (jbList.get(clickedName + currentValue).getText().equals("1")){
            p1.across(clickedName,currentValue);
         }
      }
      
      if (playerOneTurn == false){
         if (jbList.get(clickedName + currentValue).getText().equals("1")){
            p2.across(clickedName,currentValue);
         }
      }
 
      // This part decides whether or not the last marble landed in the players Mancala
      // If, so the player should go again
      
      //Player 1 landed on MancA
      if (clickedName + currentValue == 6){
         p1.enable();
         playerOneTurn = true;
      }
      
      //Player 2 landed on mancB
      else if (clickedName + currentValue == 13){
         p2.enable();
         playerOneTurn = false;
      }
      
      //Player 1 goes again 
      else if (playerOneTurn == true){
         p2.enable();
         playerOneTurn = false; 
      }
      
      //Player 2 goes again
      else if (playerOneTurn == false){
         p1.enable();
         playerOneTurn = true;
      }
      
      
   } // end of takeTurn method


   /** 
   * The end of game conditions have been met, 
   * need to put all marbles left on the board into their respective Mancalas
   */
  
   public void gameOver(){
      int finalSum1 = 0;
      int finalSum2 = 0;

      //Counts player A's pits
      for (int i = 0; i < 6; i++){
         finalSum1 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
      } 
      
      String sCurVal1 = jbList.get(6).getText();
      int curVal1 = Integer.parseInt( sCurVal1); // make the current value of the button into a number
      
      //combines the current value of all the pits with the current value of the mancalas
      int combined1 = finalSum1 + curVal1;

      String sCombined1= String.format( "%d", combined1);
      jbList.get(6).setText(sCombined1);
      
      //Counts player B's pits
      for (int i = 7; i < 13; i++){
         finalSum2 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
      }
      
      String sCurVal2 = jbList.get(13).getText();
      int curVal2 = Integer.parseInt( sCurVal2); // make the current value of the button into a number
      
      //combines the current value of all the pits with the current value of the mancalas
      int combined2 = finalSum2 + curVal2;

      String sCombined2= String.format( "%d", combined2);
      jbList.get(13).setText(sCombined2);
      
      // Checks to see which player ended with more marbles in their Mancala 
      // Printed the corresponding message 
      if (combined1 > combined2){
         JOptionPane.showMessageDialog(null, "Player 1 wins");
         int num = Integer.parseInt(p1Score.getText());
         num += 1;
         p1Score.setText(num+"");
      }
      else if (combined1 < combined2){
         JOptionPane.showMessageDialog(null, "Player 2 wins");
         int num = Integer.parseInt(p2Score.getText());
         num += 1;
         p2Score.setText(num+"");
      }
      else if (combined1 == combined2){
         System.out.println("Tie Game");
      }
      
      //After the game is over, reset it back to the beginning 
      resetGame();
      
   } //End of gameOver method 


  /** 
  * Inner class for player1 
  */

  class Player1{
  
      public Player1(){
      
      }
      
      //Method that will enable player1's buttons and disable player2's buttons 
      public void enable(){
         
        int sum1 = 0;
        
        //Enable player1's buttons unless the it doesn't have any marbles in it
        //Also sums all of player1's pits
        for (int i = 0; i < 6; i++){
            sum1 +=  Integer.parseInt(jbList.get(i).getText()); 
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         }   
 
         //Disable all of player2's buttons
         for (int i = 7; i < 13; i++){
            jbList.get(i).setEnabled(false);
         } 
         
         //If player1 doesn't have any marbles in their pits, the game is over 
         if (sum1 == 0){
            gameOver();
         }
      
      }//end of enable method   
      
      
     /** 
     * Across method checks to see if the last marble lands in an empty pit on player 1's side
     * If so, take the marbles from this and the opposite pit and put them in player 1's mancala 
     * @param clickedName String name of the button that was clicked 
     * @param currentValue Integer value of the button that was clicked
     */

      public void across(int clickedName, int currentValue){
         
         int index = clickedName + currentValue;
         String sCurVal = jbList.get(6).getText();
         int curVal = Integer.parseInt( sCurVal); // make the current value of the button into a number
         String sVal;
         int curVal_;

         switch(index){
            case 0:
               sVal = jbList.get(12).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(6).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(12).setText("0");
               break;          
            case 1:
               sVal = jbList.get(11).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(6).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(11).setText("0");
               break;
            case 2:
               sVal = jbList.get(10).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(6).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(10).setText("0");
               break; 
            case 3:
               sVal = jbList.get(9).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(6).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(9).setText("0");
               break;
            case 4:
               sVal = jbList.get(8).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(6).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(8).setText("0");
               break;
            case 5:
               sVal = jbList.get(7).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(6).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(7).setText("0");
               break;         
            default:
               break;
         }
      
      } // end of across method 
      
   } //end of Player1 class 
   
  
   /** 
   * Inner class for player2
   */
  
   public class Player2{
      
      public Player2(){
      
      }
      
      //Method that will enable player2's buttons and disable player1's buttons 
      public void enable(){
      
         int sum2 = 0;
      
         //Disable all of player1's buttons
         for (int i = 0; i < 6; i++){
            jbList.get(i).setEnabled(false);
         } 
         
         //Enable player2's buttons unless the it doesn't have any marbles in it
         //Also sums all of player2's pits
         for (int i = 7; i < 13; i++){
            sum2 +=  Integer.parseInt(jbList.get(i).getText());
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         }
         
         //If player2 doesn't have any marbles in their pits, the game is over
         if (sum2 == 0){
            gameOver();
         }
      
      } //end of enable method 
      
     
     /** 
     * Across method checks to see if the last marble lands in an empty pit on player 2's side
     * If so, take the marbles from this and the opposite pit and put them in player 2's mancala 
     * @param clickedName String name of the button that was clicked 
     * @param currentValue Integer value of the button that was clicked
     */
      
      public void across(int clickedName, int currentValue){
         
         int index = clickedName + currentValue;
         String sCurVal = jbList.get(13).getText();
         int curVal = Integer.parseInt( sCurVal); // make the current value of the button into a number
         String sVal;
         int curVal_;

         switch(index){
            case 7:
               sVal = jbList.get(5).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(13).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(5).setText("0");

               break;          
            case 8:
               sVal = jbList.get(4).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(13).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(4).setText("0");
               break;
            case 9:
               sVal = jbList.get(3).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(13).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(3).setText("0");
               break; 
            case 10:
               sVal = jbList.get(2).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(13).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(2).setText("0");
               break;
            case 11:
               sVal = jbList.get(1).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(13).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(1).setText("0");
               break;
            case 12:
               sVal = jbList.get(0).getText();
               curVal_ = Integer.parseInt( sVal);
               if (curVal_ == 0){
                  break;
               }
               curVal_ = curVal + 1 + curVal_;
               jbList.get(13).setText(""+curVal_);
               jbList.get(index).setText("0");
               jbList.get(0).setText("0");
               break;         
            default:
               break;
         
         } // End of switch
      
      } // end of across method 
   
   } //End of player2 class 
      
}//end of MancalaGame class