import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MancalaGUI extends JFrame implements ActionListener{
   
   private JButton jbutton;
   private JMenuBar jmb;
   private JMenu jm;
   private JMenuItem jmiAbout,jmiRestart,jmiExit;
   
   ArrayList<JButton> jbList = new ArrayList<JButton>();


   public static void main(String[] args){
      new MancalaGUI();
   }
   public MancalaGUI(){

      
      JButton jbutton0 = new JButton("4");
      jbutton0.setName("0");
      jbList.add(jbutton0);
      JButton jbutton1 = new JButton("4");
      jbutton1.setName("1");
      jbList.add(jbutton1);
      JButton jbutton2 = new JButton("4");
      jbutton2.setName("2");
      jbList.add(jbutton2);
      JButton jbutton3 = new JButton("4");
      jbutton3.setName("3");
      jbList.add(jbutton3);
      JButton jbutton4 = new JButton("4");
      jbutton4.setName("4");
      jbList.add(jbutton4);
      JButton jbutton5 = new JButton("4");
      jbutton5.setName("5");
      jbList.add(jbutton5);
      JButton jbutton6 = new JButton("0");
      jbutton6.setName("6");
      jbutton6.setEnabled(false);
      jbList.add(jbutton6);
      JButton jbutton7 = new JButton("4");
      jbutton7.setName("7");
      jbList.add(jbutton7);
      JButton jbutton8 = new JButton("4");
      jbutton8.setName("8");
      jbList.add(jbutton8);
      JButton jbutton9 = new JButton("4");
      jbutton9.setName("9");
      jbList.add(jbutton9);
      JButton jbutton10 = new JButton("4");
      jbutton10.setName("10");
      jbList.add(jbutton10);
      JButton jbutton11 = new JButton("4");
      jbutton11.setName("11");
      jbList.add(jbutton11);
      JButton jbutton12 = new JButton("4");
      jbutton12.setName("12");
      jbList.add(jbutton12);
      JButton jbutton13 = new JButton("0");
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
   
      //Windows settings and visibility
      pack();
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      
   }//end of constructor 
   
   
   public void actionPerformed(ActionEvent ae){
      String name = ((JButton)ae.getSource()).getName();
      takeTurn(name);
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
            
//             int resetNum = Integer.parseInt(jbList.get(0).getText());
//             resetNum = resetNum + 1;
//             String sResetNum = String.format(""+resetNum);
//             jbList.get(0).setText(sResetNum);
         }
         //System.out.println(numName);

         String sNextButtonValue = jbList.get(clickedName+i).getText(); //get the current value of the next button in line
         int nextButtonValue = Integer.parseInt( sNextButtonValue ); // make the current value of the button into a number
         
         String sNextValue = String.format( "%d", nextButtonValue+1); //add one to the current value of the current button
         jbList.get(clickedName+i).setText(sNextValue); //Update the button to hold the current value

      } // end of the loop
      
      jbList.get(clickedNameStored).setText("0"); //Set the text of the button that was clicked to 0 
      
      
   } // end of takeTurn method
   
      
}//end of MancalaGUI class