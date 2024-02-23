import javax.swing.JButton;

public class Button extends JButton {
    int row;
    int col;
    public Button(int row, int col){
        super();
        this.row = row;
        this.col = col;
    }

    int getRow(){
        return row;
    }
    int getCol(){
        return col;
    }
}
