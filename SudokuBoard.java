import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SudokuBoard extends JPanel {

    private JButton[][] buttons;
    private boolean noteOn;

    // add top and botton to layout for exit button
    // just use a png background and make wiindow non resisable
    // add scaling/actual gui component later

    public SudokuBoard(int row, int col) {
        super(new GridLayout(row, col));

        // logic stuff
        noteOn = true;

        buttons = new JButton[row][col];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                final int curRow = i;
                final int curCol = j;
                buttons[i][j] = new JButton("");
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
