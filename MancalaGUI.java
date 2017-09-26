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
      
      for(int i=0;i<6;i++){
         jbutton = new JButton("A");
         jbList.add(jbutton);
         jpMain.add(jbutton,BorderLayout.CENTER);
      
      }
      for(int i=0;i<6;i++){
         jbutton = new JButton("B");
         jbList.add(jbutton);
         jpMain.add(jbutton,BorderLayout.CENTER);
      
      }
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
   
   }
   


}//end of MancalaGUI class