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
      
      //Initializing buttons
      jbList = new ArrayList<JButton>();
      
 
//       jbutton = new JButton("5");
//       jbutton.setIcon( new ImageIcon("marble.jpg") );
//       
//       jbutton.setVerticalTextPosition(SwingConstants.BOTTOM);
//       jbutton.setHorizontalTextPosition(SwingConstants.CENTER);
//       jpMain.add(jbutton);
//       add(jpMain,BorderLayout.CENTER);
      
      
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
            System.out.println(name);
            
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
   
   String name; 
   
   public void takeTurn(String name_){
   
      //convert "name" to number 
      name = name_; 
     
      String sNum = jbList.get(1).getText(); //get the string text from jbutton
      System.out.println(sNum);
      
      int num = Integer.parseInt( sNum ); // make it a number
      num++;
      
      String sString = String.format( "%d", num);
      
      jbList.get(1).setText(sString); 
   
   }
   
}//end of MancalaGUI class