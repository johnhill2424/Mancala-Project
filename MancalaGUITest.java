import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MancalaGUITest extends JFrame implements ActionListener{
   
   private JButton jbutton;
   private JMenuBar jmb;
   private JMenu jm;
   private JMenuItem jmiAbout,jmiRestart,jmiExit;
   private Player1 p1;
   private Player2 p2;
   private boolean playerOneTurn = true; //if true, its player 1's turn, if false its player 2's turn
   private boolean endOfGame = false; 
   private boolean onMancA;
   private boolean onMancB;
   
   
   ArrayList<JButton> jbList = new ArrayList<JButton>();


   public static void main(String[] args){
      new MancalaGUITest();
   }

   public MancalaGUITest(){

      
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
   }
   
   public void initializePlayers(){
      p1 = new Player1();
      p2 = new Player2();
   }

   public void takeTurn(String name){
      
   
      int clickedName = Integer.parseInt(name); //convert the String name to number, get the button number that was clicked
      System.out.println(clickedName);
      int clickedNameStored = clickedName;
      
      String sCurrentValue = jbList.get(clickedName).getText(); //get the current value of the button that was clicked
      int currentValue = Integer.parseInt( sCurrentValue); // make the current value of the button into a number
      System.out.println(currentValue);

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
      
      
      // Should the player go again??
      //Player 1 landed on MancA
      if (clickedName + currentValue == 6){
         System.out.println("On mancA");
         p1.enable();
         playerOneTurn = true;
      }
      
      //Player 2 landed on mancB
      else if (clickedName + currentValue == 13){
         System.out.println("On mancB");
         p2.enable();
         playerOneTurn = false;
      }
      
      //player1's turn 
      else if (playerOneTurn == true){
         p2.enable();
         playerOneTurn = false; 
      }
      
      //player2's turn
      else if (playerOneTurn == false){
         p1.enable();
         playerOneTurn = true;
      }
      
      
   } // end of takeTurn method


   //Need to put all marbles left on the board into their respective columns 
   public void gameOver(){
      int finalSum1 = 0;
      int finalSum2 = 0;

      //count player A's mancalas
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
      
      if (finalSum1 > finalSum2){
         JOptionPane.showMessageDialog(null, "Player 1 wins");
      }
      else if (finalSum1 < finalSum2){
         JOptionPane.showMessageDialog(null, "Player 2 wins");
      }
      
   }   


  class Player1{//Inner class created for Player 1
  
      
      public Player1(){
      
      }
      
      //turn on player1 buttons
      public void enable(){
         
        int sum1 = 0;
        
        for (int i = 0; i < 6; i++){
            sum1 +=  Integer.parseInt(jbList.get(i).getText()); 
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         }   
 
         //disable all of player2's buttons
         for (int i = 7; i < 13; i++){
            jbList.get(i).setEnabled(false);
         } 
         
         
         if (sum1 == 0){
            gameOver();
         }
         
 
      
      }//end of enable method   
      
   } //end of Player1 class 
   
  
  
   public class Player2{//Inner class created for Player 2
      
      public Player2(){
      
      }
      
      //enable player2's buttons 
      public void enable(){
      
         //disable all of player1's buttons
         for (int i = 0; i < 6; i++){
            jbList.get(i).setEnabled(false);
         } 
         
         for (int i = 7; i < 13; i++){
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         }
      
      } //end of disable2 method 
   
   } //End of player2 class 
      
      


}//end of MancalaGUI class