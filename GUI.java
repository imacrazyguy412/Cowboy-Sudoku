import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
class GUI {
    public static JFrame cowboyFrame;
    public static StartScreen startM = new StartScreen();
    public static Difficulty diffM = new Difficulty();
    public static Game gameM = new Game();
    public static Lose lossM = new Lose();

    public void run (){
    	cowboyFrame = new JFrame();
        startM.addComponentsToPane(startM);
        startM.createAndShowGUI();
        diffM.addComponentsToPane(diffM);
        diffM.createAndShowGUI();
        lossM.addComponentsToPane(lossM);
        lossM.createAndShowGUI();
        gameM.createAndShowGUI();
        
        cowboyFrame.setContentPane(startM);
    	cowboyFrame.setSize(1000, 500);
    	cowboyFrame.repaint();
    	cowboyFrame.revalidate();
    	cowboyFrame.setVisible(true);
        
    }
    /*
     * make startscreen a panel
     * have a frame and then add startscreen to it here
     */
    public static void main(String[] args) {
    	GUI sudoku = new GUI ();
    	sudoku.run();
    	
    	
    }
    


}
