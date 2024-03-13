import javax.swing.JButton;

public class Button extends JButton {
    int row;
    int col;
    boolean isStarter, isMistake;
    public Button(int row, int col, boolean starter){
        super();
        this.row = row;
        this.col = col;
        isStarter = starter;
        isMistake = false;
    }

    int getRow(){
        return row;
    }
    int getCol(){
        return col;
    }
    boolean isStarter() {
    	return isStarter;
    }
    void setStart(boolean bool) {
    	isStarter = bool;
    }
    boolean isMistake() {
    	return isMistake;
    }
    void setMistake(boolean mis) {
    	isMistake = mis;
    }
}
