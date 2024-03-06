
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

class WinScreen extends JPanel{
    private  JLabel header;
    private  JButton back;
    public static JPanel winPan;
    ImageIcon icon;
    Image scaleImage;
    JLabel label;
    JLabel timeTaken;
     
     public WinScreen() {
    	 winPan = new JPanel();
    	 icon  = new ImageIcon(getClass().getResource("happyCowboyCat.jpg"));
    	 scaleImage  = icon.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
     }

    public  void addComponentsToPane(Container winPan) {
    	
        //Sets the content pane
    	winPan.setBackground(new Color (229,229,229));
    	winPan.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Create header label
        header = new JLabel("Wowie, you winnnn congratulations!!!");
        header.setOpaque(true);
        header.setBackground(new Color (207, 176, 100));
		header.setForeground(Color.black);
        header.setFont(new Font("Ariel", Font.PLAIN, 20));


        //set constraints and add title
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        winPan.add(header, c);
        
        

        //Create button
        back = new JButton ("Back to main menu");
        back.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        back.setOpaque(true);
        back.setBackground(new Color (36, 44, 61));
        back.setForeground (Color.white);
        back.setPreferredSize(new java.awt.Dimension(100, 100));
        back.addActionListener(event -> {
        	GUI.gameM.confFrame.dispose();
        	GUI.gameM.confFrame.remove(GUI.gameM.confFrame.getContentPane());
    		GUI.gameM = new Game();
        	GUI.gameM.createAndShowGUI();
        	GUI.cowboyFrame.setContentPane(GUI.startM);
        	GUI.cowboyFrame.repaint();
        	GUI.cowboyFrame.revalidate();
            System.out.println("frame is closed");
        });

        //set constraints and add back;
        c.gridx = 0;
        c.gridy = 2;
        winPan.add (back, c);
        
        icon = new ImageIcon(scaleImage);
        label = new JLabel(icon);
        c.fill = GridBagConstraints.SOUTH;
        c.ipady = 70;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        winPan.add(label, c);

    }
    public JComponent getPanel() {
		// TODO Auto-generated method stub
		return winPan;
	}
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
     void createAndShowGUI() {
        winPan.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	WinScreen win = new WinScreen();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                win.createAndShowGUI();
            }
        });
    }
   }
