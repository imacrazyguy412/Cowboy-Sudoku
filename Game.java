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

class Game extends JPanel{
	private JLabel diff, mist, timer;
	private JButton hint, solve, newGame, toggleNotes, undo, save;
	private SudokuBoard boardPanel;
	private Timer time;
	private int count = 0;
	public static JPanel pane;
	public static JFrame confFrame = new JFrame();
	
	
    
    public Game() {
   	 pane = new JPanel();
	 boardPanel = new SudokuBoard(9, 9);
    }
    
    private ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	count+=1;
        	timer.setText("Time: " + count);
        }
    };
    
    public void getHint(){
    	boardPanel.getHint();
    }
 
	public void addComponentsToPane (Container pane, int difficulty) {
		//Sets the content pane
		pane.setBackground(new Color (229,229,229));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		boardPanel.setDifficulty(difficulty);

		//Create diff label
		switch (difficulty){
			case 1:
				diff = new JLabel("Difficulty: Easy");
				break;
			case 2:
				diff = new JLabel("Difficulty: Medium");
				break;
			case 3:
				diff = new JLabel("Difficulty: Hard");
				break;
		}
		diff.setOpaque(true);
		diff.setBackground(new Color (207, 176, 100));
		diff.setForeground(Color.black);
		
		
		//Set constraints and add diff
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		c.insets = new Insets (0, 0, 0, 0);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		pane.add(diff, c);
		
		//add in the sudoku board to pane
		c.insets = new Insets (40, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 0;
		pane.add(boardPanel, c);

   		//Create mist label
		mist = new JLabel("Mistakes: 0");
		mist.setOpaque(true);
		mist.setBackground(new Color (207, 176, 100));
		mist.setForeground(Color.black);
		
		
		//Set constraints and add mist
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0,0,0,0);
		pane.add(mist, c);
		
		//Create time variable
		time = new Timer(1000, taskPerformer);
		time.start();


    	//Create timer label
		timer = new JLabel("Time: " + count);
		timer.setOpaque(true);
		timer.setBackground(new Color (36, 44, 61));
		timer.setForeground(Color.white);
		
		
		//Set constraints and add timer label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(timer, c);
    
		//Create hint button
		//hint.setComponentOrientation();
		hint = new JButton("Hint");
		hint.setOpaque(true);
		hint.setBackground(new Color (36, 44, 61));
		hint.setForeground (new Color (207, 176, 100));
        hint.addActionListener(event -> {
            Confirm panel = new Confirm();
            Confirm.addToPane(panel, 1);
            confFrame.add(panel);
            confFrame.setVisible(true);
        });
		
		
		//set constraints and add hint;
		c.gridx = 2;
		c.gridy = 0;
		pane.add (hint, c);

		//Create solve button
		solve = new JButton("Solve");
		solve.setOpaque(true);
		solve.setBackground(new Color (36, 44, 61));
		solve.setForeground (new Color (207, 176, 100));
        solve.addActionListener(event -> {
            Confirm panel = new Confirm();
            Confirm.addToPane(panel, 2);
            confFrame.add(panel);
            confFrame.setVisible(true);    
        });
		
		
		//set constraints and add solve;
		c.gridx = 3;
		c.gridy = 0;
		pane.add (solve, c);

		//Create newGame button
		newGame = new JButton("New Game");
		newGame.setOpaque(true);
		newGame.setBackground(new Color (36, 44, 61));
		newGame.setForeground (new Color (207, 176, 100));
        newGame.addActionListener(event -> {
            Confirm panel = new Confirm();
            Confirm.addToPane(panel, 3);
            confFrame.add(panel);
            confFrame.setVisible(true);
        });
		
		//set constraints and add newGame;
		c.gridx = 4;
		c.gridy = 0;
		pane.add (newGame, c);

		//Create toggleNotes button
		toggleNotes = new JButton("Toggle Notes");
		toggleNotes.setOpaque(true);
		toggleNotes.setBackground(new Color (36, 44, 61));
		toggleNotes.setForeground (Color.white);
		toggleNotes.addActionListener(event -> {
			//Switch to notes board
			boardPanel.toggleNote();
			
        });
		
		//set constraints and add toggleNotes;
		c.gridx = 3;
		c.gridy = 1;
		pane.add (toggleNotes, c);

		//Create undo button
		undo = new JButton("Undo");
		undo.setOpaque(true);
		undo.setBackground(new Color (207, 176, 100));
		undo.setForeground (Color.black);
		undo.addActionListener(event -> {
			//Undo action
			
        });
		
		
		//set constraints and add undo;
		c.insets = new Insets (0, 0, 0, 10);
		c.gridx = 2;
		c.gridy = 3;
		pane.add (undo, c);

		//Create save button
		save = new JButton("Save");
		save.setOpaque(true);
		save.setBackground(new Color (207, 176, 100));
		save.setForeground (Color.black);
		save.addActionListener(event -> {
           
        });
		
		//set constraints and add save;
		c.gridx = 4;
		c.gridy = 3;
		pane.add (save, c);
	}
	
    public JComponent getPanel() {
		// TODO Auto-generated method stub
		return pane;
	}
    
    public void updateTimer() {
    	
    }
    
    public void updateMistakes() {
    	
    }
    /**
/**
	* Create the GUI and show it.  For thread safety,
 	* this method should be invoked from the
  	* event-dispatching thread.
*/
    public void createAndShowGUI() {
        //Create and set up the window.
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        pane.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              //  createAndShowGUI();
            }
        });
    }
}
