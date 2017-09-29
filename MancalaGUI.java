import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MancalaGUI extends JFrame{
   
   //private JButton jb1a,jb2a,jb3a,jb4a,jb5a,jb6a;
   //private JButton jb1b,jb2b,jb3b,jb4b,jb5b,jb6b;
   private JButton jbutton;
   private ArrayList<JButton> jbList;
   private JTextArea mancA, mancB;
   private JMenuBar jmb;
   private JMenu jm;
   private JMenuItem jmiAbout,jmiRestart,jmiExit;

   public static void main(String[] args){
      new MancalaGUI();
   }
   public MancalaGUI(){
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

      
      //JPanels for the A and B row Buttons
      
      JPanel jpMain = new JPanel(new GridLayout(2,6));
      
      
      //Creat Mancala TextAreas
      mancA = new JTextArea(15,20);
      mancB = new JTextArea(15,20);
      
      mancA.setText("0");
      mancB.setText("0");
      
      //Initializing buttons
      jbList = new ArrayList<JButton>();
      
      
      for(int i=0;i<12;i++){
         jbutton = new JButton(""+i);
         jbutton.setIcon( new ImageIcon("marble.jpg") );
         jbutton.setName(""+i);
         jbutton.setText("4");
         jbutton.setVerticalTextPosition(SwingConstants.BOTTOM);
         jbutton.setHorizontalTextPosition(SwingConstants.CENTER);
         jbList.add(jbutton);
         jpMain.add(jbutton,BorderLayout.CENTER);
         jbList.get(i).addActionListener(new ActionListener(){
         public void actionPerformed (ActionEvent ae){
            String name = ((JButton)ae.getSource()).getName();
            takeTurn(name); 
            
         }// end of actionPerformed
      });//end of addActionListener
         
         
      }// end of for loop
      
      //Add Panels to JFrame
      add(jmb,BorderLayout.NORTH);
      add(mancA,BorderLayout.EAST);
      add(mancB,BorderLayout.WEST);
      add(jpMain,BorderLayout.CENTER);
      
      //JFrame Components
      
      pack();
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      

      
   }//end of constructor 
   
   
   //Get button text, turn into number, and reset
   
   
   public void takeTurn(String name){
      
      int numName = Integer.parseInt(name); //convert the String "name" to number, get the button number that was clicked
      
      String sNum = jbList.get(numName).getText(); //get the current value of the button that was clicked
      
      int num = Integer.parseInt( sNum ); // make the current value of the button into a number
      for (int i = 0; i <= num; i++){
         String sNum_ = jbList.get(numName+i).getText(); //get the current value of the next button in line
         int num_ = Integer.parseInt( sNum_ ); // make the current value of the button into a number
     
         String sString_ = String.format( "%d", num_+1); //add one to the current value
      
         jbList.get(numName+i).setText(sString_); //Update the button to hold the current value
      
      } // end of the loop
     
      jbList.get(numName).setText("0"); //Set the text of the button that was clicked to 0 
      
   } // end of takeTurn method
   
   
   
   
   
   
}//end of MancalaGUI class