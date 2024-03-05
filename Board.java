import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

public class Board implements Serializable {
	// DO NOT FORGET TO INCREMENT WHEN CHANGES ARE MADE
	@Serial
	private static final long serialVersionUID = 1L;

	private int[][] board = new int[9][9];
	private int[][][] userBoard = new int[9][9][10];
	private int difficulty; 
	private boolean valid = false;
	private int choice = 0;
	private int rando = (int) (Math.random()*10-1);
	private int rand = (int) (Math.random()*9+1);
	private int x = 0;
	private int y = 0;
	private int row = 0;
	private int col = 0;
	private int num = 0;
	private int numsPlaced = 0;
	private int targetNums = 30;
	private boolean possible = false;
	private boolean check = true;
	private Solver solver = new Solver();
	private int[][][] completedBoard;
	private int[][] realBoard;
	public Board() {
		
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		// TODO: potential random handling
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
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
				for(int r = 0; r < 9; r++) {
					for(int c = 0; c < 9; c++) {
						userBoard[r][c][0] = board[r][c];
						for(int x = 1; x < 10; x++) {
							userBoard[r][c][x] = 0;
						}
					}
				}
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
				userBoard[r][c][0] = board[r][c];
				for(int x = 1; x < 10; x++) {
					userBoard[r][c][x] = 0;
				}
			}
		}
	}
	
	public void updateBoard(int r, int c, int z, int v) {
		userBoard[r][c][z] = v;
		if(z == 0) {
			board[r][c] = v;
			for(int x = 1; x < 10; x++) {
				userBoard[r][c][1] = 0;
			}
		}
		
	}
	
	public void clearBoard() {
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				board[r][c] = 0;
				for(int x = 0; x < 10; x++) {
					userBoard[r][c][x] = 0;
				}
			}
		}
	}
	
	public int[][][] getSolvedBoard() {
		solver.solve(board);
		completedBoard = solver.getBoard();
		realBoard = new int[9][9];
		for(int i = 0; i <9; i++) {
			for (int j = 0; j<9; j++) {
				realBoard[i][j] = completedBoard[i][j][0];
			}
		}
		
		
		//print answers
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9;j ++) {
				System.out.print(completedBoard[i][j][0]);
			}
			System.out.println();
		}
		
		return completedBoard;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public int[][][] getUserBoard() {
		return userBoard;
	}
	
	public boolean isCorrect(int r, int c, int guess) {
		if(realBoard[r][c] == guess) {
			return true;
		}
		
		return false;
	}
	
	public boolean isPossible() {
		if(solver.solve(board)) {
			return true;
		}
		return false;
	}
	
	public int[] getHint() {
		int[] hint = new int[3];
		valid = false;
		while(!valid) {
			x = (int) (Math.random()*9);
			y = (int) (Math.random()*9);
			if(board[x][y] == 0) {
				hint[0] = x;
				hint[1] = y;
				int[][][] solved = getSolvedBoard();
				hint[2] = solved[x][y][0];
				valid = true;
				updateBoard(x, y, 0, getSolvedBoard()[x][y][0]);
			}
		}
		
		return hint;
	}

	public boolean gameEnd() {
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				System.out.print(userBoard[i][j][0]);
			}
			System.out.println();
		}
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				
				if(userBoard[r][c][0] == 0) {
					return false;
				}
				
			}
		}
		return true;
	}
}
