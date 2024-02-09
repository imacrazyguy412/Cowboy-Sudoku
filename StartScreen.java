import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

class StartScreen extends JPanel{
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = new JFrame("Cowboy Sudoku");
    private static final Difficulty diff = new Difficulty();

    //using to count how many times you use newgame button
    static int count = 0;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.UNKNOWN);
        }

        LineBorder line = new LineBorder(Color.black, 2, true);
        JButton button;
        pane.setLayout(new GridBagLayout());
        pane.setBackground(new Color (229,229,229,100));
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        button = new JButton("New Game");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        button.setBorder(line);
        button.setPreferredSize(new Dimension(150, 75));
        button.setBackground(new Color(54,54,54,100));
        button.setForeground(new Color(255,255,255));
        button.setVisible(true);
        button.addActionListener(event -> {
            if(count == 0) {
                frame.dispose();
                diff.createAndShowGUI();
            } else {
                frame.dispose();
                diff.secondShow();
            }
            System.out.println("frame is closed");
            count++;
        });
        pane.add(button, c);

        JLabel label = new JLabel("Cowboy Sudoku");
        label.setBackground(new Color(207,176,100));
        label.setForeground(new Color(54,54,54));
        label.setOpaque(true);
        label.setFont(new Font("Old Standard TT", Font.BOLD, 20));
        label.setBorder(line);
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

        button = new JButton("Continue Saved Game");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        button.setBorder(line);
        button.setPreferredSize(new Dimension(150, 75));
        button.setBackground(new Color(36,44,61));
        button.setForeground(new Color(255,255,255));
        button.setVisible(true);
        button.addActionListener(event -> {
            frame.dispose();
            System.out.println("frame is closed");
        });
        pane.add(button, c);

        ImageIcon icon = new ImageIcon("logo.png");
        Image scaleImage = icon.getImage().getScaledInstance(400, 400,Image.SCALE_DEFAULT);
        icon = new ImageIcon(scaleImage);
        label = new JLabel(icon);
        c.fill = GridBagConstraints.SOUTH;
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
    static void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,1200);
        frame.setResizable(false);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    static void secondShow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,1200);
        frame.setResizable(false);
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
