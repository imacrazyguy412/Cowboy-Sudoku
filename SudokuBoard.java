import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SudokuBoard extends JPanel {

    private Button[][] buttons;
    private boolean noteOn;
    Board board;
    boolean undoing = false;

    // add top and botton to layout for exit button
    // just use a png background and make wiindow non resisable
    // add scaling/actual gui component later


    //MAKE THE JBUTTON EXTENDER WITH THE ROWS AND COLUMS AFTER PLEASE YOU USELESS PERSON

    
    Board getBoard(){
    	return board;
    }
    
    public SudokuBoard(int row, int col) {
        super(new GridLayout(row, col));
        

        // logic stuff
        noteOn = false;

        buttons = new Button[row][col];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                final int curRow = i;
                final int curCol = j;
                
                
                
                buttons[i][j] = new Button(i, j, false);
               // buttons[i][j].setText();
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                
                buttons[i][j].addKeyListener(enter);
                buttons[i][j].addKeyListener(number);
                buttons[i][j].setBackground(Color.white);
                buttons[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                if (curRow > 0) {
                                	if(!buttons[curRow][curCol].isStarter) {
                                		buttons[curRow][curCol].setBackground(Color.white);
                                		buttons[curRow - 1][curCol].setBackground(new Color(173, 216, 230));
                                	} else {
                                		buttons[curRow][curCol].setBackground(Color.gray);
                                		buttons[curRow - 1][curCol].setBackground(new Color(173, 216, 230));
                                	}
                                }
                                    
                                buttons[curRow - 1][curCol].requestFocus();
                                
                                // buttons[curRow - 1][curCol].se
                                break;
                            case KeyEvent.VK_DOWN:
                                if (curRow < buttons.length - 1) {
                                	if(!buttons[curRow][curCol].isStarter) {
                                		buttons[curRow][curCol].setBackground(Color.white);
                                		buttons[curRow +1][curCol].setBackground(new Color(173, 216, 230));
                                	} else {
                                		buttons[curRow][curCol].setBackground(Color.gray);
                                		buttons[curRow +1][curCol].setBackground(new Color(173, 216, 230));
                                	}
                                }
                                buttons[curRow + 1][curCol].requestFocus();
                                break;
                            case KeyEvent.VK_LEFT:
                                if (curCol > 0) {
                                	if(!buttons[curRow][curCol].isStarter) {
                                		buttons[curRow][curCol].setBackground(Color.white);
                                		buttons[curRow][curCol-1].setBackground(new Color(173, 216, 230));
                                	} else {
                                		buttons[curRow][curCol].setBackground(Color.gray);
                                		buttons[curRow][curCol-1].setBackground(new Color(173, 216, 230));
                                	}
                                }
                                buttons[curRow][curCol - 1].requestFocus();
                                break;
                            case KeyEvent.VK_RIGHT:
                                if (curCol < buttons[curRow].length - 1) {
                                	if(!buttons[curRow][curCol].isStarter) {
                                		buttons[curRow][curCol].setBackground(Color.white);
                                		buttons[curRow][curCol+1].setBackground(new Color(173, 216, 230));
                                	} else {
                                		buttons[curRow][curCol].setBackground(Color.gray);
                                		buttons[curRow][curCol+1].setBackground(new Color(173, 216, 230));
                                	}
                                }
                                buttons[curRow][curCol + 1].requestFocus();
                                break;
                            default:
                                break;
                        }
                    }
                });
                add(buttons[i][j]);
            }
        }
    }
    

    void setNote(Button button, int note) {
    	
        if (button.getText().contains(note + "")) {
            System.out.println(button.getText());
            System.out.println("removed" + note);
            button.setText(button.getText().substring(0, button.getText().indexOf(note + "")) +
                button.getText().substring(button.getText().indexOf(note + "") + 1));
        } else {
            if (button.getText().equals("")) {
            	if(!button.isStarter() && button.getText().length()<2)
                button.setText(note + "");
            } else {
            	if(!button.isStarter() && button.getText().length()<2) {
                for (int i = 0; i < button.getText().length(); i++) {
                    System.out.print(i);
                    if (Integer.parseInt(button.getText().substring(i, i + 1)) > note) {
                        button.setText(button.getText().substring(0, i) + note + button.getText().substring(i));
                        return;
                    }
                }
                button.setText(button.getText() + note);
            	}
            }

        }
    	
    	
    	
    	
    	
    }
    
    
    void toggleNote(){
        noteOn = !noteOn;
    }

    void setDifficulty(int difficulty){
        board = new Board();
        board.randomGenBoard(difficulty);
        board.getSolvedBoard();
        for(int i = 0; i<9; i++) {
        	for(int j = 0;j <9; j++) {
        		if(board.getBoard()[i][j] != 0) {
        			buttons[i][j].setText(" " + board.getBoard()[i][j] + "  ");
        			buttons[i][j].setBackground(Color.gray);
        			buttons[i][j].setStart(true);
        		}
        	}
        }
        
        
    }
    void setDifficulty(){
        board.getSolvedBoard();
        for(int i = 0; i<9; i++) {
        	for(int j = 0;j <9; j++) {
        		if(board.getBoard()[i][j] != 0) {
        			buttons[i][j].setText(" " + board.getBoard()[i][j] + "  ");
        			
                    if(board.isCorrect(i, j, board.getBoard()[i][j])){}
        			buttons[i][j].setStart(true);
                    buttons[i][j].setBackground(Color.gray);
                } else if(board.getBoard()[i][j] != 0){
                    buttons[i][j].setBackground(new Color(255, 204, 203));
                }
        		}
        	}
        }
        
        
    
    void getHint(){
    	
    	int[] nums = board.getHint();
    	buttons[nums[0]][nums[1]].setText(nums[2] + "");
    }
    
    
    void solve() {
    	for(int i = 0; i< 9; i++) {
    		for(int j = 0; j<9; j++) {
    			setNum(buttons[i][j], board.getRealBoard()[i][j], i, j);
    			setNum(buttons[i][j], board.getRealBoard()[i][j], i, j);
    		}
    	}
    }
    
    void undo() {
    	int[] undo = board.undo();
    	undoing = true;

    	if(!board.isCorrect(undo[0], undo[1], undo[3])) {
    		setNum(buttons[undo[0]][undo[1]], undo[3], undo[0], undo[1]);
    	}
    	undoing = false;
    }
    
    void setNum(Button button, int guess, int row, int col){
    	if(!button.isStarter()) {
    		if(guess != 0) {
    	    	
    	        button.setText(guess +"");
    	        System.out.println("row: " + row + " col: " +col);

    	        //CALLING BOARD IN HERE
    	        
    	        board.updateBoard(row, col, 0, guess);
    	        if(!board.isCorrect(row, col, guess)) {
    	        	button.setBackground(new Color(255, 204, 203));
    	        	//UPDATE MISTAKES HERE
    	        	if(!undoing) {
    	            	GUI.gameM.updateMistakes();
    	        	}
    	        } else if (button.isStarter){
    	        	button.setBackground(Color.gray);
    	        } else {
    	        	button.setBackground(Color.white);
    	        }
    	        System.out.println(board.isCorrect(row, col, guess));
    	    	} else {
    	    	button.setText("");
    	    	board.updateBoard(row, col, 0, guess);
    	    	button.setBackground(Color.white);
    	    	}
        }
    	if(board.isCorrect(row, col, guess)) {
			buttons[row][col].setStart(true);
		}
    	
    	//checkgame end
    	if(board.gameEnd()) {
		System.out.print("win... gen screen now plaese");
        	GUI.gameM.confFrame.setContentPane(GUI.winM);
        	GUI.gameM.confFrame.setSize(720, 360);
        	GUI.gameM.confFrame.repaint();
        	GUI.gameM.confFrame.revalidate();
        	GUI.gameM.confFrame.setVisible(true);

    	}

    }
    void setNum(Button button, int num){
        button.setText(num +"");
    }
    
    
    void loadGame(Board board) {
    	this.board = board;
    	setDifficulty();
    	
    }
    
    
    
    
    
    
    
    
    
    
    

    private KeyListener enter = new KeyAdapter() {
        @Override public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                ((Button) e.getComponent()).doClick();
            }
        }
    };
    private KeyListener number = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (noteOn) {
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_1:
                        setNote((Button)(e.getComponent()), 1);
                        break;
                    case KeyEvent.VK_2:
                        setNote((Button)(e.getComponent()), 2);
                        break;
                    case KeyEvent.VK_3:
                        setNote((Button)(e.getComponent()), 3);
                        break;
                    case KeyEvent.VK_4:
                        setNote((Button)(e.getComponent()), 4);
                        break;
                    case KeyEvent.VK_5:
                        setNote((Button)(e.getComponent()), 5);
                        break;
                    case KeyEvent.VK_6:
                        setNote((Button)(e.getComponent()), 6);
                        break;
                    case KeyEvent.VK_7:
                        setNote((Button)(e.getComponent()), 7);
                        break;
                    case KeyEvent.VK_8:
                        setNote((Button)(e.getComponent()), 8);
                        break;
                    case KeyEvent.VK_9:
                        setNote((Button)(e.getComponent()), 9);
                        break;
                }


            } else {
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_1:
                        setNum((Button)(e.getComponent()), 1, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_2:
                        setNum((Button)(e.getComponent()), 2, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_3:
                        setNum((Button)(e.getComponent()), 3, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_4:
                        setNum((Button)(e.getComponent()), 4, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_5:
                        setNum((Button)(e.getComponent()), 5, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_6:
                        setNum((Button)(e.getComponent()), 6, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_7:
                        setNum((Button)(e.getComponent()), 7, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_8:
                        setNum((Button)(e.getComponent()), 8, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                    case KeyEvent.VK_9:
                        setNum((Button)(e.getComponent()), 9, ((Button)(e.getComponent())).getRow(), ((Button)(e.getComponent())).getCol());
                        break;
                }
            }
        }
    };

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new SudokuBoard(9, 9));
        // f.pack();
        f.setSize(400, 400);
        f.setVisible(true);
    }
}
