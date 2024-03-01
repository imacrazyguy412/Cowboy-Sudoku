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
		Board make = new Board();
		
		//Creates a board and checks if that board is solvable
		board = make.randomGenBoard(2);
		
		//Printing results
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(make.getBoard()[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(make.getSolvedBoard()[r][c][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(r + " " + c + "  ");
				if(!(make.getBoard()[r][c] == 0)) {
					numsPlaced++;
				}
				for(int z = 0; z < 10; z++) {
					System.out.print(make.getSolvedBoard()[r][c][z] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println(numsPlaced + " numbers placed");
		System.out.print(solver.getAttempts() + " attempt");
		if(solver.getAttempts() > 1) {
			System.out.println("s");
		} else {
			System.out.println();
		}
		int[] hint = make.getHint();
		System.out.println(hint[0] + ", " + hint[1] + ": " + hint[2]);
		
		make.updateBoard(hint[0], hint[1], 0, hint[2]);
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(make.getUserBoard()[r][c][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
		hint = make.getHint();
		make.updateBoard(hint[0], hint[1], 0, hint[2]);
		System.out.println(hint[0] + ", " + hint[1] + ": " + hint[2]);
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(make.getUserBoard()[r][c][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
		make.undo();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(make.getUserBoard()[r][c][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
     
}
