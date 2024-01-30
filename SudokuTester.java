import java.math.*;
public class SudokuTester {
	public static void main(String[] args) {
		int[][] board = new int[9][9];
		int rando = (int) (Math.random()*10-1);
		int rand = (int) (Math.random()*9+1);
		boolean valid = false;
		Solver solver = new Solver();
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				board[x][y] = 0;
			}
		}
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				rand = (int) (Math.random()*9+1);
				rando = (int) (Math.random()*10-1);
				valid = false;
				if(rando <= 2) {
					if(solver.notFull(board, x, y)) {
						//System.out.println(board[x][y] + " " + rand + " " + solver.valid(board, rand, x, y) + " " + x + " " + y);
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
					}
				}
			}
		}

		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				System.out.print(board[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
		solver.solve(board);
		int[][][] board2 = solver.getBoard();
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				System.out.print(x + " " + y + "  ");
				for(int z = 0; z < 10; z++) {
					System.out.print(board2[x][y][z] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	
}
