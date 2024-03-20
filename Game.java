import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;

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
	public static int mistakes = 0;
	private int mistakeLimit;
	private boolean mistakeOn = true;
	private boolean notesToggled = false;
	private Border defaultBorder;
	private Board savedBoard;
	
	private int minute;
	private String lastTime;
	
	
    
    public Game() {
   	 pane = new JPanel();
   	 //boardPanel not getting changed here into round
   	 //boardPanel.putClientProperty( "JButton.buttonType", "roundRect" );
	 boardPanel = new SudokuBoard(3, 3);
    }
    private ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	count+=1;
        	if(count < 10 && minute < 10) {
        		timer.setText("Time: 0" + minute + ":0" + count);
        	} else if(count >=10 && minute < 10){
        		timer.setText("Time: 0" + minute + ":" + count);
        	} else if(count < 10 && minute >= 10) {
        		timer.setText("Time: " + minute + ":0" + count);
        	} else if(count >= 10 && minute >= 10){
        		timer.setText("Time: " + minute + ":" + count);
        	}
        	
        	if(count == 59) {
        		minute++;
        		count = 0;
        	}
        }
    };
    
    public void getHint(){
    	boardPanel.getHint();
    }
 
	public void addComponentsToPane (Container pane, int difficulty, Board board) {
		defaultBorder = BorderFactory.createLineBorder(null);
		//Sets the content pane
		pane.setBackground(new Color (229,229,229));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		savedBoard = board;
		
		if(board != null) {
				//Printing results
			System.out.println("Loading...");
			boardPanel.loadGame(savedBoard);
			difficulty = savedBoard.getDifficulty();
		}
		
		else {
		boardPanel.setDifficulty(difficulty);
		}
		
		

		//Create diff label
		switch (difficulty){
			case 1:
				diff = new JLabel("Difficulty: Easy");
				mistakeLimit = 5;
				break;
			case 2:
				diff = new JLabel("Difficulty: Medium");
				mistakeLimit = 4;
				break;
			case 3:
				diff = new JLabel("Difficulty: Hard");
				mistakeLimit = 3;
				break;
		}
		diff.setPreferredSize(new Dimension(70, 30));
		diff.setFont(new Font("Serif", Font.PLAIN, 20));
		diff.setHorizontalAlignment(SwingConstants.CENTER);
		diff.setVerticalAlignment(SwingConstants.CENTER);
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
		mist = new JLabel("Mistakes remaining: " + (mistakeLimit - mistakes));
		mist.setPreferredSize(new Dimension(70, 50));
		mist.setFont(new Font("Serif", Font.PLAIN, 20));
		mist.setHorizontalAlignment(SwingConstants.CENTER);
		mist.setVerticalAlignment(SwingConstants.CENTER);
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
		// + count
		timer = new JLabel("Time: 00:00");
		timer.setPreferredSize(new Dimension(70, 50));
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
            confFrame.setContentPane(panel);
            confFrame.setSize(1280, 720);
            confFrame.repaint();
            confFrame.revalidate();
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
            confFrame.setContentPane(panel);
            confFrame.setSize(1280, 720);
            confFrame.repaint();
            confFrame.revalidate();
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
            confFrame.setContentPane(panel);
            confFrame.setSize(1280, 720);
            confFrame.repaint();
            confFrame.revalidate();
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
		toggleNotes.setPreferredSize(new Dimension(200, 40));
		toggleNotes.addActionListener(event -> {
			/*if (notesToggled) {
				notesToggled = false;
				toggleNotes.setBorder(defaultBorder);
				System.out.println(notesToggled);
			} else {
				notesToggled = true;
				toggleNotes.setBorder(new BevelBorder(BevelBorder.LOWERED));
	            toggleNotes.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	            System.out.println(notesToggled);
			}
			*/
			boardPanel.toggleNote();	
        });
		
		//set constraints and add toggleNotes;
		c.gridx = 3;
		c.gridy = 3;
		pane.add (toggleNotes, c);

		//Create undo button
		undo = new JButton("Undo");
		undo.setOpaque(true);
		undo.setBackground(new Color (207, 176, 100));
		undo.setForeground (Color.black);
		undo.setPreferredSize(new Dimension(70, 40));
		undo.addActionListener(event -> {
			//Undo action
			boardPanel.undo();
        });
		
		
		//set constraints and add undo;
		c.insets = new Insets (0, 0, 0, 0);
		c.gridx = 2;
		c.gridy = 3;
		pane.add (undo, c);

		//Create save button
		save = new JButton("Save");
		save.setOpaque(true);
		save.setBackground(new Color (207, 176, 100));
		save.setForeground (Color.black);
		save.setPreferredSize(new Dimension(70, 40));
		save.addActionListener(event -> {
			
			
			
			try {
			File file = new File("board.ser");
			file.createNewFile();
			System.out.println("saved");
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false));
			outStream.writeObject(boardPanel.getBoard());
			outStream.flush();
			outStream.close();
			} catch(IOException e) {
				System.out.println(e);
			}
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
    
    public String getLastTime() {
    	return lastTime = timer.getText();
    }
    
    public void updateMistakes() {
    	if(mistakeOn) {
    	mistakes++;
	if ((mistakeLimit - mistakes) <= 0){
		GUI.gameM = new Game();
        	GUI.gameM.createAndShowGUI();
		GUI.cowboyFrame.setContentPane(GUI.lossM);
        	GUI.cowboyFrame.repaint();
        	GUI.cowboyFrame.revalidate();
        	mistakes = 0;
	} else 
    		mist.setText("Mistakes remaining: " + (mistakeLimit - mistakes));
	
    	}
    }
    
    SudokuBoard getSudokuBoard(){
    	return boardPanel;
    }
    /**
/**
	* Create the GUI and show it.  For thread safety,
 	* this method should be invoked from the
  	* event-dispatching thread.
  	* 
*/
    public void createAndShowGUI() {
        //Create and set up the window.
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
    	pane.repaint();
    	pane.revalidate();
        pane.setVisible(true);
    }
    
    public void setMistakes(int mist) {
    	mistakes = mist;
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
