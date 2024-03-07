import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.*;

public class SudokuTester {
	public static void main(String[] args)  {
		Board board = new Board();
		
		//Creates a board and checks if that board is solvable
		board.randomGenBoard(2);
		board.getSolvedBoard();
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				System.out.print(board.getRealBoard()[i][j]);
			}
			System.out.println();
		}
		
		try {
		//Printing results
		File file = new File("board.ser");
		ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, true));
		outStream.writeObject(board);
		outStream.flush();
		outStream.close();
		

		ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
		Board b = (Board) inStream.readObject();
		inStream.close();
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				System.out.print(b.getRealBoard()[i][j]);
			}
			System.out.println();
		}
		} catch(IOException e) {
			System.out.println(e);
		} catch(ClassNotFoundException h) {
			System.out.println(h);
		}
		
	}

}
