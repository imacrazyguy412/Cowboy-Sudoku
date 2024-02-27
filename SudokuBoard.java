import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SudokuBoard extends JPanel {

    private Button[][] buttons;
    private boolean noteOn;
    Board board;

    // add top and botton to layout for exit button
    // just use a png background and make wiindow non resisable
    // add scaling/actual gui component later


    //MAKE THE JBUTTON EXTENDER WITH THE ROWS AND COLUMS AFTER PLEASE YOU USELESS PERSON

    public SudokuBoard(int row, int col) {
        super(new GridLayout(row, col));
        

        // logic stuff
        noteOn = false;

        buttons = new Button[row][col];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                final int curRow = i;
                final int curCol = j;
                
                
                
                buttons[i][j] = new Button(i, j);
               // buttons[i][j].setText();
                
                
                buttons[i][j].addKeyListener(enter);
                buttons[i][j].addKeyListener(number);
                buttons[i][j].setBackground(Color.white);
                buttons[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                if (curRow > 0)
                                    buttons[curRow][curCol].setBackground(Color.white);
                                buttons[curRow - 1][curCol].requestFocus();
                                buttons[curRow - 1][curCol].setBackground(new Color(173, 216, 230));
                                // buttons[curRow - 1][curCol].se
                                break;
                            case KeyEvent.VK_DOWN:
                                if (curRow < buttons.length - 1)
                                    buttons[curRow][curCol].setBackground(Color.white);
                                buttons[curRow + 1][curCol].requestFocus();
                                buttons[curRow + 1][curCol].setBackground(new Color(173, 216, 230));
                                break;
                            case KeyEvent.VK_LEFT:
                                if (curCol > 0)
                                    buttons[curRow][curCol].setBackground(Color.white);
                                buttons[curRow][curCol - 1].requestFocus();
                                buttons[curRow][curCol - 1].setBackground(new Color(173, 216, 230));
                                break;
                            case KeyEvent.VK_RIGHT:
                                if (curCol < buttons[curRow].length - 1)
                                    buttons[curRow][curCol].setBackground(Color.white);
                                buttons[curRow][curCol + 1].requestFocus();
                                buttons[curRow][curCol + 1].setBackground(new Color(173, 216, 230));
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
    

    void setNote(JButton button, int note) {

        if (button.getText().contains(note + "")) {
            System.out.println(button.getText());
            System.out.println("removed" + note);
            button.setText(button.getText().substring(0, button.getText().indexOf(note + "")) +
                button.getText().substring(button.getText().indexOf(note + "") + 1));
        } else {
            if (button.getText().equals("")) {
                button.setText(note + "");
            } else {
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
        			buttons[i][j].setText(board.getBoard()[i][j] + "");
        			buttons[i][j].setBackground(Color.gray);
        		}
        	}
        }
        
        
    }

    void setNum(JButton button, int guess, int row, int col){
        button.setText(guess +"");
        System.out.println("row: " + row + " col: " +col);

        //CALLING BOARD IN HERE
        
        board.updateBoard(row, col, guess, 0);
        if(!board.isCorrect(row, col, guess)) {
        	button.setBackground(new Color(255, 204, 203));
        	//UPDATE MISTAKES HERE
        	GUI.gameM.updateMistakes();
        } else {
        	button.setBackground(Color.white);
        }
        System.out.println(board.isCorrect(row, col, guess));




    }
    void setNum(JButton button, int num){
        button.setText(num +"");
    }

    private KeyListener enter = new KeyAdapter() {
        @Override public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                ((JButton) e.getComponent()).doClick();
            }
        }
    };
    private KeyListener number = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (noteOn) {
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_1:
                        setNote((JButton)(e.getComponent()), 1);
                        break;
                    case KeyEvent.VK_2:
                        setNote((JButton)(e.getComponent()), 2);
                        break;
                    case KeyEvent.VK_3:
                        setNote((JButton)(e.getComponent()), 3);
                        break;
                    case KeyEvent.VK_4:
                        setNote((JButton)(e.getComponent()), 4);
                        break;
                    case KeyEvent.VK_5:
                        setNote((JButton)(e.getComponent()), 5);
                        break;
                    case KeyEvent.VK_6:
                        setNote((JButton)(e.getComponent()), 6);
                        break;
                    case KeyEvent.VK_7:
                        setNote((JButton)(e.getComponent()), 7);
                        break;
                    case KeyEvent.VK_8:
                        setNote((JButton)(e.getComponent()), 8);
                        break;
                    case KeyEvent.VK_9:
                        setNote((JButton)(e.getComponent()), 9);
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
