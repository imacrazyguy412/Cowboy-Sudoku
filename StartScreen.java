import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

 class StartScreen extends JPanel{
	 final static boolean shouldFill = true;
	 final static boolean shouldWeightX = true;
	 final static boolean RIGHT_TO_LEFT = false;

	 public static void addComponentsToPane(Container pane) {
		 if (RIGHT_TO_LEFT) {
			 pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			 }

	    JButton button;
		pane.setLayout(new GridBagLayout());
		pane.setBackground(new Color (229,229,229,100));
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
		//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		button = new JButton("Button 1");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		button.setBackground(new Color(54,54,54,100));
		button.setForeground(new Color(255,255,255));
		pane.add(button, c);

		JLabel label = new JLabel("Cowboy Sudoku");
		label.setBackground(new Color(207,176,100));
		label.setForeground(new Color(54,54,54));
		label.setOpaque(true);
		label.setFont(new Font("Old Standard TT", Font.BOLD, 20));
		c.fill = GridBagConstraints.NORTH;
		//c.ipady = 70; 
		//c.ipadx = 50;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		label.setPreferredSize(new Dimension(300, 75));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		pane.add(label, c);

		button = new JButton("Button 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		button.setPreferredSize(new Dimension(100, 75));
		button.setBackground(new Color(36,44,61));
		button.setForeground(new Color(255,255,255));
		pane.add(button, c);

		label = new JLabel(" ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 70;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(label, c);

		/*button = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		pane.add(button, c);*/
	    }

	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event-dispatching thread.
	     */
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("GridBagLayoutDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Set up the content pane.
	        addComponentsToPane(frame.getContentPane());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
}
