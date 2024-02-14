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

class Easy {
	private static JLabel diff, mist, timer;
	private static JButton hint, solve, newGame, toggleNotes, numSelect, undo, save;
  static JFrame frame = new JFrame("Cowboy Sudoku");
 
	public static void addToPane (Container pane) {
		//Sets the content pane
		pane.setBackground(new Color (229,229,229));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Create diff label
		diff = new JLabel("Difficulty Selection");
		diff.setOpaque(true);
		diff.setBackground(new Color (207, 176, 100));
		diff.setForeground(Color.black);
		
		
		//Set constraints and add diff
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(diff, c);

   		 //Create mist label
		mist = new JLabel("Mistakes: 0");
		mist.setOpaque(true);
		mist.setBackground(new Color (207, 176, 100));
		mist.setForeground(Color.black);
		
		
		//Set constraints and add mist
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(mist, c);

    		//Create timer label
		timer = new JLabel("Time: 0");
		timer.setOpaque(true);
		timer.setBackground(new Color (36, 44, 61));
		timer.setForeground(Color.white);
		
		
		//Set constraints and add timer
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(timer, c);
    
		//Create hint button
		hint = new JButton("Hint");
		hint.setOpaque(true);
		hint.setBackground(new Color (36, 44, 61));
		hint.setForeground (new Color (207, 176, 100));
		
		//set constraints and add hint;
		c.gridx = 2;
		c.gridy = 0;
		pane.add (hint, c);

		//Create solve button
		solve = new JButton("Solve");
		solve.setOpaque(true);
		solve.setBackground(new Color (36, 44, 61));
		solve.setForeground (new Color (207, 176, 100));
		
		//set constraints and add solve;
		c.gridx = 3;
		c.gridy = 0;
		pane.add (solve, c);

		//Create newGame button
		newGame = new JButton("New Game");
		newGame.setOpaque(true);
		newGame.setBackground(new Color (36, 44, 61));
		newGame.setForeground (new Color (207, 176, 100));
		
		//set constraints and add newGame;
		c.gridx = 4;
		c.gridy = 0;
		pane.add (newGame, c);

		//Create toggleNotes button
		toggleNotes = new JButton("Toggle Notes");
		toggleNotes.setOpaque(true);
		toggleNotes.setBackground(new Color (36, 44, 61));
		toggleNotes.setForeground (Color.white);
		
		//set constraints and add toggleNotes;
		c.gridx = 4;
		c.gridy = 1;
		pane.add (toggleNotes, c);

		//Create numSelect button
		numSelect = new JButton("Number Select");
		numSelect.setOpaque(true);
		numSelect.setBackground(new Color (54, 54, 54));
		numSelect.setForeground (Color.white);
		
		//set constraints and add toggleNotes;
		c.gridx = 4;
		c.gridy = 2;
		pane.add (numSelect, c);

		//Create undo button
		undo = new JButton("Number Select");
		undo.setOpaque(true);
		undo.setBackground(new Color (207, 176, 100));
		undo.setForeground (Color.black);
		
		//set constraints and add undo;
		c.gridx = 3;
		c.gridy = 3;
		pane.add (undo, c);

		//Create save button
		undo = new JButton("Number Select");
		undo.setOpaque(true);
		undo.setBackground(new Color (207, 176, 100));
		undo.setForeground (Color.black);
		
		//set constraints and add save;
		c.gridx = 3;
		c.gridy = 3;
		pane.add (undo, c);

		
		

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
