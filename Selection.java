import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

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

class Selection {
    private static JLabel header;
    private static JButton bOne, bTwo, bThree, bFour, bFive, bSix, bSeven, bEight, bNine, back;
    static JFrame frame = new JFrame("Cowboy Sudoku");

    public static void addToPane(Container pane) {
        //Sets the content pane
        pane.setBackground(new Color(229, 229, 229));
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Create header label
        header = new JLabel("Number Select");
        header.setOpaque(true);
        header.setBackground(new Color(207, 176, 100));
        header.setForeground(Color.black);


        //set constraints and add title
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(header, c);

        //Create bOne
        bOne = new JButton("1");
        bOne.setOpaque(true);
        bOne.setBackground(new Color(36, 44, 61));
        bOne.setForeground(Color.white);

        //set constraints and add bOne;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(bOne, c);

        //Create bTwo
        bTwo = new JButton("2");
        bTwo.setOpaque(true);
        bTwo.setBackground(new Color(36, 44, 61));
        bTwo.setForeground(Color.white);

        //set constraints and add easy;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(bTwo, c);

        //Create bThree
        bThree = new JButton("3");
        bThree.setOpaque(true);
        bThree.setBackground(new Color(36, 44, 61));
        bThree.setForeground(Color.white);

        //set constraints and add bThree;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(bThree, c);

        //Create bFour
        bFour = new JButton("4");
        bFour.setOpaque(true);
        bFour.setBackground(new Color(36, 44, 61));
        bFour.setForeground(Color.white);

        //set constraints and add bFour;
        c.gridx = 1;
        c.gridy = 4;
        pane.add(bFour, c);

        //Create bFive
        bFive = new JButton("5");
        bFive.setOpaque(true);
        bFive.setBackground(new Color(36, 44, 61));
        bFive.setForeground(Color.white);

        //set constraints and add bFive;
        c.gridx = 1;
        c.gridy = 5;
        pane.add(bFive, c);

        //Create bSix
        bSix = new JButton("6");
        bSix.setOpaque(true);
        bSix.setBackground(new Color(36, 44, 61));
        bSix.setForeground(Color.white);

        //set constraints and add bSix;
        c.gridx = 1;
        c.gridy = 6;
        pane.add(bSix, c);

        //Create bSeven
        bSeven = new JButton("7");
        bSeven.setOpaque(true);
        bSeven.setBackground(new Color(36, 44, 61));
        bSeven.setForeground(Color.white);

        //set constraints and add bSeven;
        c.gridx = 1;
        c.gridy = 7;
        pane.add(bSeven, c);

        //Create bEight
        bEight = new JButton("8");
        bEight.setOpaque(true);
        bEight.setBackground(new Color(36, 44, 61));
        bEight.setForeground(Color.white);

        //set constraints and add bEight;
        c.gridx = 1;
        c.gridy = 8;
        pane.add(bEight, c);

        //Create bNine
        bNine = new JButton("9");
        bNine.setOpaque(true);
        bNine.setBackground(new Color(36, 44, 61));
        bNine.setForeground(Color.white);

        //set constraints and add bNine;
        c.gridx = 1;
        c.gridy = 9;
        pane.add(bNine, c);

        //Create back
        back = new JButton("Back");
        back.setOpaque(true);
        back.setBackground(new Color(36, 44, 61));
        back.setForeground(Color.white);

        //set constraints and add back;
        c.gridx = 0;
        c.gridy = 10;
        pane.add(back, c);
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addToPane(frame.getContentPane());

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
