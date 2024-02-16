import java.util.Scanner;
import java.util.InputMismatchException;
public class BoardMaker {
	int[][] board = new int[9][9];
	int difficulty; 
	boolean valid = false;
	int choice = 0;
	int rando = (int) (Math.random()*10-1);
	int rand = (int) (Math.random()*9+1);
	int x = 0;
	int y = 0;
	int row = 0;
	int col = 0;
	int num = 0;
	int numsPlaced = 0;
	int targetNums = 30;
	boolean possible = false;
	boolean check = true;
	Solver solver = new Solver();
	public BoardMaker() {
		
	}
	
	public int[][] randomGenBoard(int diff) {
		difficulty = diff; //diff is the difficulty. 1: easy. 2: normal. 3: hard.
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				board[r][c] = 0;
			}
		}
		valid = false;
		if(difficulty == 1) {
			targetNums = 35;
		} else if(difficulty == 2) {
			targetNums = 30;
		} else {
			targetNums = 25;
		}
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
		return board;
	}
	
	public void setFullBoard(int[][] bord) {
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				board[r][c] = bord[r][c];
			}
		}
	}
	
	public void setOneVal(int r, int c, int v) {
		board[r][c] = v;
	}
	
	public void clearBoard() {
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				board[r][c] = 0;
			}
		}
	}
	
	public int[][][] getSolvedBoard() {
		solver.solve(board);
		int[][][] completeBoard = solver.getBoard();
		return completeBoard;
	}
	
	public boolean isPossible() {
		if(solver.solve(board)) {
			return true;
		}
		return false;
	}
}
