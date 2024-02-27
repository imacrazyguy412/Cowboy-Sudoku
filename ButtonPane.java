import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonPane extends JPanel {

   private JButton[][] buttons;

    //add top and botton to layout for exit button
    //just use a png background and make wiindow non resisable
    //add scaling/actual gui component later

   public ButtonPane(int row, int col) {
      super(new GridLayout(row, col));
      buttons = new JButton[row][col];
      for (int i = 0; i < buttons.length; i++) {
         for (int j = 0; j < buttons[i].length; j++) {
            final int curRow = i;
            final int curCol = j;
            buttons[i][j] = new JButton("");
            buttons[i][j].addKeyListener(enter);
            buttons[i][j].setBackground(Color.white);
            buttons[i][j].addKeyListener(new KeyAdapter() {
               @Override
               public void keyPressed(KeyEvent e) {
                  switch (e.getKeyCode()) {
                  case KeyEvent.VK_UP:
                     if (curRow > 0)
                    	buttons[curRow][curCol].setBackground(Color.white);
                        buttons[curRow - 1][curCol].requestFocus();
                     	buttons[curRow - 1][curCol].setBackground(Color.blue);
                    // buttons[curRow - 1][curCol].se
                     break;
                  case KeyEvent.VK_DOWN:
                     if (curRow < buttons.length - 1)
                    	buttons[curRow][curCol].setBackground(Color.white);
                        buttons[curRow + 1][curCol].requestFocus();
                     	buttons[curRow + 1][curCol].setBackground(Color.BLUE);
                     break;
                  case KeyEvent.VK_LEFT:
                     if (curCol > 0)
                    	buttons[curRow][curCol].setBackground(Color.white);
                        buttons[curRow][curCol - 1].requestFocus();
                     	buttons[curRow][curCol - 1].setBackground(Color.BLUE);
                     break;
                  case KeyEvent.VK_RIGHT:
                     if (curCol < buttons[curRow].length - 1)
                    	buttons[curRow][curCol].setBackground(Color.white);
                        buttons[curRow][curCol + 1].requestFocus();
                        buttons[curRow][curCol + 1].setBackground(Color.BLUE);
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

   private KeyListener enter = new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            ((JButton) e.getComponent()).doClick();
         }
      }
   };

   public static void main(String[] args) {
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.add(new ButtonPane(9, 9));
      //f.pack();
      f.setSize(400,400);
      f.setVisible(true);
   }
}
