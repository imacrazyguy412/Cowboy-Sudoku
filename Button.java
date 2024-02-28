import javax.swing.JButton;

public class Button extends JButton {
    int row;
    int col;
    boolean isStarter;
    public Button(int row, int col, boolean starter){
        super();
        this.row = row;
        this.col = col;
        isStarter = starter;
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
}
