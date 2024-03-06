import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.*;

public class SudokuTester {
	public static void main(String[] args) {
		//Sample boards used for testing, keep these around just in case
		/*int[][] board = new int[][] {{5,3,0,0,7,0,0,0,0},
									{6,0,0,1,9,5,0,0,0},
									{0,9,8,0,0,0,0,6,0},
									{8,0,0,0,6,0,0,0,3},
									{4,0,0,8,0,3,0,0,1},
									{7,0,0,0,2,0,0,0,6},
									{0,6,0,0,0,0,2,8,0},
									{0,0,0,4,1,9,0,0,5},
									{0,0,0,0,8,0,0,7,9}};*/
		/*int[][] board = new int[][]  {  {7, 0, 0, 0, 0, 0, 2, 0, 0},   
	          {4, 0, 2, 0, 0, 0, 0, 0, 3},   
	          {0, 0, 0, 2, 0, 1, 0, 0, 0},   
	          {3, 0, 0, 1, 8, 0, 0, 9, 7},   
	          {0, 0, 9, 0, 7, 0, 6, 0, 0},   
	          {6, 5, 0, 0, 3, 2, 0, 0, 1},   
	          {0, 0, 0, 4, 0, 9, 0, 0, 0},   
	          {5, 0, 0, 0, 0, 0, 1, 0, 6},   
	          {0, 0, 6, 0, 0, 0, 0, 0, 8}   
	       }; */  
		/*int[][] board = new int[][] { {3, 0, 6, 5, 0, 8, 4, 0, 0},
			{5, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 8, 7, 0, 0, 0, 0, 3, 1},
			{0, 0, 3, 0, 1, 0, 0, 8, 0},
			{9, 0, 0, 8, 6, 3, 0, 0, 5},
			{0, 5, 0, 0, 9, 0, 6, 0, 0}, 
			{1, 3, 0, 0, 0, 0, 2, 5, 0},
			{0, 0, 0, 0, 0, 0, 0, 7, 4},
			{0, 0, 5, 2, 0, 6, 3, 0, 0} };*/
		
		int[][] board = new int[9][9];
		int rando = (int) (Math.random()*10-1);
		int rand = (int) (Math.random()*9+1);
		int x = 0;
		int y = 0;
		int numsPlaced = 0;
		int targetNums = 30;
		boolean valid = false;
		boolean possible = false;
		Solver solver = new Solver();
		BoardMaker make = new BoardMaker();
		
		//Creates a board and checks if that board is solvable
		board = make.randomGenBoard(2);
		
		//Printing results
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
		solver.solve(board);
		int[][][] board2 = solver.getBoard();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(board2[r][c][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(r + " " + c + "  ");
				if(!(board[r][c] == 0)) {
					numsPlaced++;
				}
				for(int z = 0; z < 10; z++) {
					System.out.print(board2[r][c][z] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println(numsPlaced + " numbers placed");
		System.out.print(solver.getAttempts() + " attempt");
		if(solver.getAttempts() > 1) {
			System.out.println("s");
		}

		File file = new File("board.ser");
		ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, true));
		outStream.writeObject(new Board());
		outStream.flush();
		outStream.close();

		ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
		Board b = (Board) inStream.readObject();
		inStream.close();
	}
     
}
