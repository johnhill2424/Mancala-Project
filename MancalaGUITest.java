import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class MancalaGUITest extends JFrame implements ActionListener{
   
   private JButton jbutton;
   private JMenuBar jmb;
   private JMenu jm;
   private JMenuItem jmiAbout,jmiRestart,jmiExit,jmiP2win,jmiP1win, jmiSave, jmiLoadGame;
   private Player1 p1;
   private Player2 p2;
   private boolean playerOneTurn = true; //if true, its player 1's turn, if false its player 2's turn
   private boolean endOfGame = false; 
   private boolean onMancA;
   private boolean onMancB;
   
   
   ArrayList<JButton> jbList = new ArrayList<JButton>();
   ArrayList<ImageIcon> imgList = new ArrayList<ImageIcon>();
   
   
   
   JLabel p1Score = new JLabel("0");
   JLabel p2Score = new JLabel("0");

   public static void main(String[] args){
      new MancalaGUITest();
   }

   public MancalaGUITest(){

      
      for(int i = 0; i <= 13; i++){
         if(i == 6 || i == 13){
            JButton jbutton = new JButton("0");
            jbutton.setFont(new Font("Arial", Font.PLAIN, 40));
            jbutton.setEnabled(false);
            jbutton.setName(""+i);
            jbList.add(jbutton);
         }
         else{
            JButton jbutton = new JButton("4");
            jbutton.setFont(new Font("Arial", Font.PLAIN, 40));
            jbutton.setName(""+i);
            jbList.add(jbutton);
         }
         
      }

    
      for(int i = 0; i <= 10; i++){
         try {
            ImageIcon img = new ImageIcon("manc_marbles/marble"+i+".png");
            imgList.add(img);
            
         }
         catch(Exception e){
            System.out.println(e);
         }
      }
     
      marbleAmount();
 

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
      
      //JPanel superJP = new JPanel();
      //superJP.add(jpMain, BorderLayout.CENTER);
      
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
      jmiP1win = new JMenuItem("P1 Wins");
      jmiP2win = new JMenuItem("P2 Wins");
      jmiSave = new JMenuItem("Save");
      jmiLoadGame = new JMenuItem("Load Game");

      
      jm.add(jmiP1win);
      jm.add(jmiP2win);
      jm.add(jmiAbout);
      jm.add(jmiRestart);
      jm.add(jmiExit);
      jm.add(jmiSave);
      jm.add(jmiLoadGame);
      
      jmiP1win.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            
            jbList.get(0).setText("0");
            jbList.get(1).setText("0");
            jbList.get(2).setText("0");
            jbList.get(3).setText("2");
            jbList.get(4).setText("0");
            jbList.get(5).setText("0");
            jbList.get(6).setText("40");
            jbList.get(7).setText("3");
            jbList.get(8).setText("0");
            jbList.get(9).setText("0");
            jbList.get(10).setText("2");
            jbList.get(11).setText("0");
            jbList.get(12).setText("1");
            jbList.get(13).setText("0");
            
            marbleAmount();
            playerOneTurn = true;
            endOfGame = false;
            
         }
      });
      
      jmiP2win.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            
            jbList.get(0).setText("3");
            jbList.get(1).setText("0");
            jbList.get(2).setText("0");
            jbList.get(3).setText("2");
            jbList.get(4).setText("0");
            jbList.get(5).setText("1");
            jbList.get(6).setText("0");
            jbList.get(7).setText("0");
            jbList.get(8).setText("0");
            jbList.get(9).setText("0");
            jbList.get(10).setText("2");
            jbList.get(11).setText("0");
            jbList.get(12).setText("0");
            jbList.get(13).setText("40");
            
            marbleAmount();
            playerOneTurn = true;
            endOfGame = false;
            
         }
      });
      
      jmiRestart.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            resetGame();
            p1Score.setText("0");
            p2Score.setText("0");
            
         }
      });
      
      jmiExit.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            System.exit(0);
         }
      });
      
      
      jmiSave.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            save();
         }
      });
      
      jmiLoadGame.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            load();
         }
      });
      
      //add menubar to menupanel
      jmbPanel.add(jmb);
      
      
      p1Score.setFont(new Font("Arial", Font.PLAIN, 30));
      p1Score.setForeground(Color.RED);
      p2Score.setFont(new Font("Arial", Font.PLAIN, 30));
      p2Score.setForeground(Color.BLUE);
      
      JLabel jlP1 = new JLabel("Scores| Player 1: ");
      jlP1.setFont(new Font("Arial", Font.PLAIN, 30));
      
      JLabel jlP2 = new JLabel(" Player 2: ");
      jlP2.setFont(new Font("Arial", Font.PLAIN, 30));
      
      jmbPanel.add(jlP1);
      jmbPanel.add(p1Score);
      jmbPanel.add(jlP2);
      jmbPanel.add(p2Score);

      
      //add menupanel to main panel
      jpMain.add(jmbPanel,BorderLayout.NORTH);

      //add to frame
      add(jpMain);
      //add(jmbPanel, BorderLayout.WEST);
   
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
      marbleAmount();
   }
   
   public void initializePlayers(){
      p1 = new Player1();
      p2 = new Player2();
   }
   
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
   
   public void save(){
   
      try{
         File game1 = new File("game1.txt");
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
      
      } // end of catch 

   
   } //end of save method 
   
   
   public void load(){
   
      try{
         BufferedReader br = new BufferedReader(new FileReader("game1.txt"));
         String output = br.readLine();
         
         String[] split = output.split(" ");
         for (int i = 0; i < jbList.size(); i++){
            jbList.get(i).setText(split[i]);
         }
         
         p1Score.setText(split[14]);
         p2Score.setText(split[15]);
         
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
      
      } // end of catch 

   
   } //end of load method 
   
   public void marbleAmount(){//Method takes the current amount of marbles in pocket, and matches corresponding picture
      
      for(int i=0; i<jbList.size(); i++){
         String sNum = jbList.get(i).getText();
         int num = Integer.parseInt(sNum);
         
         
         if(num > 15){
            //System.out.println("Num = "+num);
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

   public void takeTurn(String name){
      
   
      int clickedName = Integer.parseInt(name); //convert the String name to number, get the button number that was clicked
      //System.out.println(clickedName);
      int clickedNameStored = clickedName;
      
      String sCurrentValue = jbList.get(clickedName).getText(); //get the current value of the button that was clicked
      int currentValue = Integer.parseInt( sCurrentValue); // make the current value of the button into a number
      //System.out.println(currentValue);

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
      

      
      // Should the player go again??
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
      
      resetGame();
      
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
   
  
  
   public class Player2{//Inner class created for Player 2
      
      public Player2(){
      
      }
      
      //enable player2's buttons 
      public void enable(){
      
         int sum2 = 0;
      
         //disable all of player1's buttons
         for (int i = 0; i < 6; i++){
            jbList.get(i).setEnabled(false);
         } 
         
         for (int i = 7; i < 13; i++){
            sum2 +=  Integer.parseInt(jbList.get(i).getText());
            if (jbList.get(i).getText() == "0"){
               jbList.get(i).setEnabled(false);
            }
            else{
               jbList.get(i).setEnabled(true);
            }
         }
         
         if (sum2 == 0){
            gameOver();
         }
      
      } //end of disable2 method 
      
      
      
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
         
         }
      
      
      } // end of across method 
   
   } //End of player2 class 
      
      


}//end of MancalaGUI class