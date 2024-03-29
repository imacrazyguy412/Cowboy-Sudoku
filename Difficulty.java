
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.swing.BorderFactory;

/*
	Constraints reference
 	c.fill = GridBagConstraints.HORIZONTAL;
  	c.ipady
   	c.ipadx
   	c.gridwidth
    	c.gridheight
     	c.gridy
      	c.gridx
    	c.anchor
     	c.insets
  	c.weightx = 0.0;
	c.weighty = 0.0;
*/

class Difficulty extends JPanel{
    private  JLabel header;
    private  JButton easy, medium, hard, back, start, mistakeToggle;
    public  static JPanel Difframe;
    Border defaultBorder;
    

    //easy: 1, medium: 2, hard: 3
     int gameDiff;
     
     public Difficulty() {
    	 Difframe = new JPanel();
     }

    public  void addComponentsToPane(Container Difframe) {
    	defaultBorder = BorderFactory.createLineBorder(new Color (229,229,229));
        //Sets the content pane
    	Difframe.setBackground(new Color (229,229,229));
    	Difframe.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Create header label
        header = new JLabel("Difficulty Selection");
        header.setOpaque(true);
        header.setBackground(new Color (36, 44, 61));
        header.setForeground(Color.white);


        //set constraints and add title
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        Difframe.add(header, c);

        //Create buttons
        easy = new JButton("Easy");
        easy.setOpaque(true);
        easy.setBackground(new Color (207, 176, 100));
        easy.setForeground (Color.black);
      //setting default border in order to change later
        easy.setBorder(defaultBorder);
        easy.setPreferredSize(new Dimension(30, 30));
        easy.addActionListener(event -> {
            gameDiff = 1;
            easy.setBorder(new BevelBorder(BevelBorder.LOWERED));
            easy.setBorder(BorderFactory.createLineBorder(Color.black));
            //change other buttons back to normal
            medium.setBorder(defaultBorder);
            hard.setBorder(defaultBorder);
        });
        //set constraints and add easy;
        c.gridx = 1;
        c.gridy = 1;
        Difframe.add (easy, c);

        medium = new JButton ("Medium");
        medium.setOpaque(true);
        medium.setBackground(new Color (207, 176, 100));
        medium.setForeground (Color.black);
        //medium.setBackground(new Color (207, 176, 100));
        medium.setBorder(defaultBorder);
        medium.setPreferredSize(new Dimension(30, 30));
        medium.addActionListener(event -> {
            gameDiff = 2;
            medium.setBorder(new BevelBorder(BevelBorder.LOWERED));
            medium.setBorder(BorderFactory.createLineBorder(Color.black));
            //change other buttons back to normal
            easy.setBorder(defaultBorder);
            hard.setBorder(defaultBorder);
        });
        //set constraints and add medium;
        c.gridx = 1;
        c.gridy = 2;
        Difframe.add (medium, c);

        hard = new JButton ("Hard");
        hard.setOpaque(true);
        hard.setBackground(new Color (207, 176, 100));
        hard.setForeground (Color.black);
        hard.setBorder(defaultBorder);
        hard.setPreferredSize(new Dimension(30, 30));
        hard.addActionListener(event -> {
            gameDiff = 3;
            hard.setBorder(new BevelBorder(BevelBorder.LOWERED));
            hard.setBorder(BorderFactory.createLineBorder(Color.black));
            //change other buttons back to normal
            easy.setBorder(defaultBorder);
            medium.setBorder(defaultBorder);
         });
        //set constraints and add hard;
        c.gridx = 1;
        c.gridy = 3;
        Difframe.add (hard, c);

        back = new JButton ("Back");
        back.setOpaque(true);
        back.setBackground(new Color (36, 44, 61));
        back.setForeground (Color.white);
        
        back.addActionListener(event -> {
        	GUI.cowboyFrame.setContentPane(GUI.startM);
        	GUI.cowboyFrame.repaint();
        	GUI.cowboyFrame.revalidate();
            System.out.println("frame is closed");
        });

        //set constraints and add back;
        c.gridx = 0;
        c.gridy = 4;
        Difframe.add (back, c);


        start = new JButton ("Start");

        start.setBackground(new Color (36, 44, 61));
        start.setForeground (Color.white);
        start.addActionListener(event -> {
            GUI.gameM.addComponentsToPane(GUI.gameM, gameDiff, null);
            GUI.cowboyFrame.setContentPane(GUI.gameM);
        	GUI.cowboyFrame.repaint();
        	GUI.cowboyFrame.revalidate();
        });
        //set constraints and add start;
        c.gridx = 2;
        c.gridy = 4;
        Difframe.add (start, c);


       /*
       mistakeToggle = new JButton ("Toggle Mistakes");
        mistakeToggle.setOpaque(true);
        mistakeToggle.setBackground(new Color (36, 44, 61));
        mistakeToggle.setForeground (Color.white);
        mistakeToggle.addActionListener(event -> {
        	
        });
        

        //set constraints and add mistakeToggle;
        c.gridx = 0;
        c.gridy = 0;
        Difframe.add(mistakeToggle, c); 
	*/

    }
    public JComponent getPanel() {
		// TODO Auto-generated method stub
		return Difframe;
	}
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
     void createAndShowGUI() {
        //Create and set up the window.
    	 //Difframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        //addToPane(Difframe.getContentPane());

        //Display the window.
        //Difframe.pack();
        Difframe.setVisible(true);
        //Difframe.setResizable(false);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	Difficulty diff = new Difficulty();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                diff.createAndShowGUI();
            }
        });
    }
    
    public void backToStart() {
        	Difframe.setVisible(false);
            //newStartScreen.secondShow();
            System.out.println("frame is closed");
        }
    }


