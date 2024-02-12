import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
class GUI {
    private String state;

    public void run (){
        switch (state){
            case "start":
            	
                //draw screen
                break;
            case "difficulty":
                //draw screen
                break;
            case "play":
                //draw screen
                break;
        }
    }
    /*
     * make startscreen a panel
     * have a frame and then add startscreen to it here
     */
    public static void main(String[] args) {
    	StartScreen start = new StartScreen();
    	start.createAndShowGUI();
    }

    

}
