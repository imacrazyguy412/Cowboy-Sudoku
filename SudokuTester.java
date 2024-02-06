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
	       };  */ 
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
		int targetNums = 25;
		boolean valid = false;
		boolean possible = false;
		Solver solver = new Solver();
		
		//Creates a board and checks if that board is solvable
		while(!possible) {
			while(numsPlaced < targetNums) {
				rand = (int) (Math.random()*9+1);
				rando = (int) (Math.random()*10+1);
				x = (int) (Math.random()*9);
				y = (int) (Math.random()*9);
				valid = false;
				if(rando <= 3) {
					if(board[x][y] == 0 && solver.notFull(board, x, y)) {
						if(solver.valid(board, rand, x, y)) {
							valid = true;
						}
						while(!valid) {
							rand = (int) (Math.random()*9+1);
							if(solver.valid(board, rand, x, y)) {
								valid = true;
							}
						}
						board[x][y] = rand;
						numsPlaced++;
					}
				}
			}
			if(solver.solve(board)) {
				possible = true;
				numsPlaced = 0;
			} else {
				for(int r = 0; r < 9; r++) {
					for(int c = 0; c < 9; c++) {
						board[r][c] = 0;
					}
				}
				numsPlaced = 0;
			}
		}
		
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
		
	}
     
}
