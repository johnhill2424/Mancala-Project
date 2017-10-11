import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MancalaGUI extends JFrame implements ActionListener{
   
   private JButton jbutton;
   private JMenuBar jmb;
   private JMenu jm;
   private JMenuItem jmiAbout,jmiRestart,jmiExit;
   private Player1 p1;
   private Player2 p2;
   private boolean playerTurn = false; //if true, its player 1's turn, if false its player 2's turn
   private boolean endOfGame = false; 
   private boolean onMancA;
   private boolean onMancB;
   
   
   ArrayList<JButton> jbList = new ArrayList<JButton>();


   public static void main(String[] args){
      new MancalaGUI();
   }

   public MancalaGUI(){

      
      JButton jbutton0 = new JButton("04");
      jbutton0.setName("0");
      jbList.add(jbutton0);
      JButton jbutton1 = new JButton("04");
      jbutton1.setName("1");
      jbList.add(jbutton1);
      JButton jbutton2 = new JButton("04");
      jbutton2.setName("2");
      jbList.add(jbutton2);
      JButton jbutton3 = new JButton("04");
      jbutton3.setName("3");
      jbList.add(jbutton3);
      JButton jbutton4 = new JButton("04");
      jbutton4.setName("4");
      jbList.add(jbutton4);
      JButton jbutton5 = new JButton("04");
      jbutton5.setName("5");
      jbList.add(jbutton5);
      JButton jbutton6 = new JButton("00");
      jbutton6.setName("6");
      jbutton6.setEnabled(false);
      jbList.add(jbutton6);
      JButton jbutton7 = new JButton("04");
      jbutton7.setName("7");
      jbList.add(jbutton7);
      JButton jbutton8 = new JButton("04");
      jbutton8.setName("8");
      jbList.add(jbutton8);
      JButton jbutton9 = new JButton("04");
      jbutton9.setName("9");
      jbList.add(jbutton9);
      JButton jbutton10 = new JButton("04");
      jbutton10.setName("10");
      jbList.add(jbutton10);
      JButton jbutton11 = new JButton("04");
      jbutton11.setName("11");
      jbList.add(jbutton11);
      JButton jbutton12 = new JButton("04");
      jbutton12.setName("12");
      jbList.add(jbutton12);
      JButton jbutton13 = new JButton("00");
      jbutton13.setName("13");
      jbutton13.setEnabled(false);
      jbList.add(jbutton13);
 
 
//          jbutton.setIcon( new ImageIcon("marble.jpg") );
//          jbutton.setVerticalTextPosition(SwingConstants.BOTTOM);
//          jbutton.setHorizontalTextPosition(SwingConstants.CENTER);
 

		JPanel topRow = new JPanel();
		for(int i=12; i>=7; i--){ 
         topRow.add(jbList.get(i));
      }
		
		JPanel bottomRow = new JPanel();
		for(int i=0; i<=5; i++){
         bottomRow.add(jbList.get(i));
      }

		JPanel bothRows = new JPanel();
		bothRows.setLayout( new GridLayout(2, 1) );
		bothRows.add(topRow);
		bothRows.add(bottomRow);

		// Combine the two lines with the mancalas on both sides
		JPanel jpMain = new JPanel();	// Create another container
      jpMain.setLayout(new BorderLayout());
      jpMain.add(bothRows, BorderLayout.CENTER);
      jpMain.add(jbList.get(13), BorderLayout.WEST);
      jpMain.add(jbList.get(6), BorderLayout.EAST);
      
      JPanel jmbPanel = new JPanel();
      
      //MenuBar
      jmb = new JMenuBar();
      
      //JMenu
      jm = new JMenu("Settings");
      jmb.add(jm);
      
      //JMenuItems
      jmiAbout = new JMenuItem("About");
      jmiRestart = new JMenuItem("Restart");
      jmiExit = new JMenuItem("Exit");
      
      jm.add(jmiAbout);
      jm.add(jmiRestart);
      jm.add(jmiExit);
      
      jmiExit.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            System.exit(0);
         }
      });
      
      //add menubar to menupanel
      jmbPanel.add(jmb);
      
      //add menupanel to main panel
      jpMain.add(jmbPanel,BorderLayout.NORTH);

      //add to frame
      add(jpMain);
   
   
      //Add actionListeners to all buttons
      for(int i=0;i<14;i++){      
         jbList.get(i).addActionListener(this);
      }
   
   
      //disable player 2's buttons
      for(int i=7; i<=12;i++){
         jbList.get(i).setEnabled(false);
      }

      //Windows settings and visibility
      pack();
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      
      initializePlayers();
      
     
   }//end of constructor 
   
   
   public void actionPerformed(ActionEvent ae){
      String name = ((JButton)ae.getSource()).getName();
      takeTurn(name);
      disabled();
   }
   
   public void initializePlayers(){
      int player1Manc = Integer.parseInt(jbList.get(6).getText());
      p1 = new Player1(player1Manc);
      int player2Manc = Integer.parseInt(jbList.get(13).getText());
      p2 = new Player2(player2Manc);
   }
   
   
   int sum1 = 0;
   int sum2 = 0;
   
   
   public void disabled(){
   
      //Player 1 takes turn
      if (playerTurn == true && onMancA == false){
         for (int i = 7; i < 13; i++){
            jbList.get(i).setEnabled(false);
            sum1 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
         } 
         
         for (int i = 0; i < 6; i++){
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         }

         if (sum1 == 0){
            gameOver();
         }
         
         sum1 = 0;
         
         playerTurn = false;
         
      }
      
      //Player 1 takes second turn
      else if (playerTurn == true && onMancA == true){
         for (int i = 7; i < 13; i++){
            jbList.get(i).setEnabled(false);
            sum1 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
         } 
         
         for (int i = 0; i < 6; i++){
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         }

         if (sum1 == 0){
            gameOver();
         }
         
         sum1 = 0;
         
         playerTurn = true;
         onMancA = false;
         
      }
 
      //Player 2 takes second turn
      else if (playerTurn == false && onMancB == true){
         for (int i = 7; i < 13; i++){
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         } 
         
         for (int i = 0; i < 6; i++){
            jbList.get(i).setEnabled(false);
            sum2 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
         }

         if (sum1 == 0){
            gameOver();
         }
         
         sum2 = 0;
         
         playerTurn = true;
         onMancB = false;
         
      }
 
         
      //Player 2 takes turn 
      else if (playerTurn == false && onMancB == false){
         for (int i = 7; i < 13; i++){
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         } 
         
         for (int i = 0; i < 6; i++){
            jbList.get(i).setEnabled(false);
            sum2 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
         }
         
         
         if (sum2 == 0){
            gameOver();
         }
         
         sum2 = 0;
         playerTurn = true;
      }
   
  
   }//End of disabled
   
   
   //Need to put all marbles left on the board into their respective columns 
   public void gameOver(){
      int finalSum1 = 0;
      int finalSum2 = 0;

      for (int i = 0; i < 6; i++){
         finalSum1 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
      } 
      
      String sCurVal1 = jbList.get(6).getText();
      int curVal1 = Integer.parseInt( sCurVal1); // make the current value of the button into a number
      int combined1 = finalSum1 + curVal1;

      String sCombined1= String.format( "%d", combined1);
      jbList.get(6).setText(sCombined1);
      
      
      for (int i = 7; i < 13; i++){
         finalSum2 +=  Integer.parseInt(jbList.get(i).getText()); //get the current value of the button that was clicked
      }
      
      String sCurVal2 = jbList.get(13).getText();
      int curVal2 = Integer.parseInt( sCurVal2); // make the current value of the button into a number
      int combined2 = finalSum2 + curVal2;

      String sCombined2= String.format( "%d", combined2);
      jbList.get(13).setText(sCombined2);
      
   }
   

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
      
      // Should the player 
      
      //Player 1 landed on MancA
      if (clickedName + currentValue == 6){
         boolean onMancA = true;
      }
      
      //Player 2
      else if (clickedName + currentValue == 13){
         boolean onMancB = true;
      }
      
      
   } // end of takeTurn method
   
  
  
  public class Player1{//Inner class created for Player 1
   
      private int manc_amount;//attribute that checks the amount of marbles are in Player 1's mancala
      
      public Player1(int manc_amount){//Player 1's constructor takes in the amount in the mancala and makes an attribute
      
         this.manc_amount = manc_amount;
      
      }
      
      public int getManc1(){
         return manc_amount;
      }
      
      public void setManc1(int mancAmount){
         manc_amount = mancAmount; 
      }
   
   
   }
   
   public class Player2{//Inner class created for Player 2
   
      private int manc_amount;//attribute that checks the amount of marbles are in Player 2's mancala
      
      public Player2(int manc_amount){//Player 2's constructor takes in the amount in the mancala and makes an attribute
      
         this.manc_amount = manc_amount;
      
      }
   
   }
      
      
      
      
}//end of MancalaGUI class