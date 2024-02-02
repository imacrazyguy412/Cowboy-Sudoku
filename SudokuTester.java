import java.math.*;

public class SudokuTester {
	public static void main(String[] args) {
		int[][] board = new int[][] {{5,3,0,0,7,0,0,0,0},
									{6,0,0,1,9,5,0,0,0},
									{0,9,8,0,0,0,0,6,0},
									{8,0,0,0,6,0,0,0,3},
									{4,0,0,8,0,3,0,0,1},
									{7,0,0,0,2,0,0,0,6},
									{0,6,0,0,0,0,2,8,0},
									{0,0,0,4,1,9,0,0,5},
									{0,0,0,0,8,0,0,7,9}};
		int rando = (int) (Math.random()*10-1);
		int rand = (int) (Math.random()*9+1);
		boolean valid = false;
		Solver solver = new Solver();
		
		/*for(int y = 0; y < 9; y++) {
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
		}*/

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
				for(int z = 0; z < 10; z++) {
					System.out.print(board2[r][c][z] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
	}

}
