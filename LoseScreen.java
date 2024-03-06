import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class LoseScreen extends JPanel {
	private static JLabel header;
	private static JButton back;
	public static JPanel loseP;
	ImageIcon icon;
    Image scaleImage;
    JLabel label;
	//static JFrame frame = new JFrame("Cowboy Sudoku");
	
    public LoseScreen() {
    	icon  = new ImageIcon(getClass().getResource("sadCowboyCat.jpg"));
    	scaleImage  = icon.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
    }
 
	public void addToPane (Container pane) {
		
		
		//Sets the content pane
		pane.setBackground(new Color (229,229,229));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Create header label
		header = new JLabel("You lose! You had too many mistakes!\r\n"
				+ "");
		header.setOpaque(true);
		header.setBackground(new Color (207, 176, 100));
		header.setForeground(Color.black);
		header.setFont(new Font("Ariel", Font.PLAIN, 20));
		
		//set constraints and add header
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(header, c);
		
		back = new JButton ("Return to main menu");
		back.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		back.setOpaque(true);
		back.setBackground(new Color (54, 54, 54));
		back.setForeground (Color.white);
		back.setPreferredSize(new java.awt.Dimension(100, 100));
		back.addActionListener(event -> {
        	//Game.confFrame.dispose();
			GUI.gameM = new Game();
        	GUI.gameM.createAndShowGUI();
            GUI.cowboyFrame.setContentPane(GUI.startM);
            GUI.cowboyFrame.repaint();
            GUI.cowboyFrame.revalidate();
        });
		
		//set constraints and add conf;
		c.gridwidth = 400;
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 3;
		pane.add (back, c);
		
		icon = new ImageIcon(scaleImage);
        label = new JLabel(icon);
        c.fill = GridBagConstraints.SOUTH;
        c.ipady = 70;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(label, c);
		
	}
/**
	* Create the GUI and show it.  For thread safety,
 	* this method should be invoked from the
  	* event-dispatching thread.
*/
    public  void createAndShowGUI() {
    	/*
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        */
        loseP.setVisible(true);
    }
/*
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    */
}
