import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

class StartScreen extends JPanel {

    boolean shouldFill;
    boolean shouldWeightX;
    boolean RIGHT_TO_LEFT;
    JPanel panel;
    ImageIcon icon;
    Image scaleImage;
    public static StartScreen screen;
    public StartScreen() {
        shouldFill = true;
        shouldWeightX = true;
        RIGHT_TO_LEFT = false;
        panel = new JPanel();
        icon = new ImageIcon(getClass().getResource("logo.png"));
        scaleImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

    }

    public void addComponentsToPane(Container panel) {

        if (RIGHT_TO_LEFT) {
            panel.setComponentOrientation(ComponentOrientation.UNKNOWN);
        }

        LineBorder line = new LineBorder(Color.black, 2, true);
        JButton button;
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(229, 229, 229, 100));
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
        button.setBackground(new Color(54, 54, 54, 100));
        button.setForeground(new Color(255, 255, 255));
        button.setVisible(true);

        button.addActionListener(event - > {
            GUI.cowboyFrame.setContentPane(GUI.diffM);
            System.out.println("frame is closed");
        });
        panel.add(button, c);

        JLabel label = new JLabel("Cowboy Sudoku");
        label.setBackground(new Color(207, 176, 100));
        label.setForeground(new Color(54, 54, 54));
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
        panel.add(label, c);

        button = new JButton("Continue Saved Game");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        button.setBorder(line);
        button.setPreferredSize(new Dimension(150, 75));
        button.setBackground(new Color(36, 44, 61));
        button.setForeground(new Color(255, 255, 255));
        button.setVisible(true);
        button.addActionListener(event - > {
            GUI.cowboyFrame.setContentPane(GUI.diffM);
            System.out.println("frame is closed");
        });
        panel.add(button, c);

        icon = new ImageIcon(scaleImage);
        label = new JLabel(icon);
        c.fill = GridBagConstraints.SOUTH;
        c.ipady = 70; //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(label, c);

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
    void createAndShowGUI() {
        //Create and set up the window.
        //panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(900, 1200);
        // panel.setResizable(false);

        //Set up the content pane.
        //addComponentsToPane(panel.getContentPane());

        //Display the window.
        //panel.pack();
        panel.setVisible(true);
    }
    void secondShow() {
        //panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(900, 1200);
        //panel.setResizable(false);
        //panel.pack();
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        //screen = new StartScreen();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //screen.createAndShowGUI();
            }
        });
    }


}