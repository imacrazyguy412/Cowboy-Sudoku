import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.InputMismatchException;
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int[][] board = new int[9][9];
	int[][][] userBoard = new int[9][9][10];
	int difficulty; 
	boolean valid = false;
	int choice = 0;
	int rando = (int) (Math.random()*10-1);
	int val = (int) (Math.random()*9+1);
	int x = 0;
	int y = 0;
	int row = 0;
	int col = 0;
	int num = 0;
	int numsPlaced = 0;
	int targetNums = 30;
	int temp = 0;
	boolean possible = false;
	boolean check = true;
	Solver solver = new Solver();
	int[][][] completedBoard;
	int[][] realBoard;
	int[] undo = new int[4];
	int mistakes;

	public Board() {
		
	}
	
	void setMistakes(int mis){
		mistakes = mis;
		System.out.println(mis);
	}
	
	int getMistakes(){
		return mistakes;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
	public int[][] getRealBoard(){
		getSolvedBoard();
		return realBoard;
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
			targetNums = 31;
		} else if(difficulty == 2) {
			targetNums = 28;
		} else {
			targetNums = 25;
		}
		while(!possible) {
			while(numsPlaced < targetNums) {
				val = (int) (Math.random()*9+1);
				x = (int) (Math.random()*9);
				y = (int) (Math.random()*9);
				valid = false;
				if(board[x][y] == 0 && solver.notFull(board, x, y)) {
					if(solver.valid(board, val, x, y)) {
						board[x][y] = val;
						numsPlaced++;
					}
				}
			}
			if(solver.solve(board)) {
				completedBoard = solver.getBoard();
				for (int r = 0; r < 9; r++) {
					for (int c = 0; c < 9; c++) {
						if(temp == 0) {
							for (int rowt = 0; rowt < 9; rowt++) {
								if (!(rowt == r)) {
									if (completedBoard[rowt][c][0] == completedBoard[r][c][0]) {
										possible = false;
										temp++;
									}
								}
							}
							for (int colt = 0; colt < 9; colt++) {
								if (!(colt == c)) {
									if (completedBoard[r][colt][0] == completedBoard[r][c][0]) {
										possible = false;
										temp++;
									}
								}
							}
							for (int rowt = r / 3 * 3; rowt < r / 3 * 3 + 3; rowt++) {
								for (int colt = c / 3 * 3; colt < c / 3 * 3 + 3; colt++) {
									if ((!(colt == c)) && (!(rowt == r))) {
										if (completedBoard[rowt][colt][0] == completedBoard[r][c][0]) {
											possible = false;
											temp++;
										}
									}
								}
							}
						}
					}
				}
				if(temp == 0) {
					possible = true;
					numsPlaced = 0;
					temp = 0;
					if(difficulty == 1) {
						while (temp < 5) {
							x = (int) (Math.random()*9);
							y = (int) (Math.random()*9);
							if(board[x][y] == 0) {
								board[x][y] = completedBoard[x][y][0];
								temp++;
							}
						}
						temp = 0;
					}
					for(int r = 0; r < 9; r++) {
						for(int c = 0; c < 9; c++) {
							userBoard[r][c][0] = board[r][c];
							for(int x = 1; x < 10; x++) {
								userBoard[r][c][x] = 0;
							}
						}
					}
					for(int i = 0; i<9; i++) {
						for(int j = 0; j<9;j ++) {
							System.out.print(completedBoard[i][j][0]);
						}
						System.out.println();
					}
					System.out.println();
					for(int i = 0; i<9; i++) {
						for(int j = 0; j<9;j ++) {
							System.out.print(board[i][j]);
						}
						System.out.println();
					}
					System.out.println();
				} else {
					for(int r = 0; r < 9; r++) {
						for(int c = 0; c < 9; c++) {
							board[r][c] = 0;
						}
					}
					numsPlaced = 0;
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
		getSolvedBoard();
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9;j ++) {
				System.out.print(completedBoard[i][j][0]);
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9;j ++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
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
		if(z == 0) {
			undo[0] = r;
			undo[1] = c;
			undo[2] = z;
			undo[3] = getVal(r, c, z);
		}
		
		userBoard[r][c][z] = v;
		if(z == 0) {
			if(v == realBoard[r][c]) {
				board[r][c] = v;
			}
			
			for(int x = 1; x < 10; x++) {
				userBoard[r][c][1] = 0;
			}
		}
		
	}
	
	public int[] undo() {
		return undo;
	}
	
	public void removeVal(int r, int c, int v) {
		if(userBoard[r][c][0] == v) {
			userBoard[r][c][0] = 0;
		} else {
			boolean shift = false;
			for(int x = 1; x < 9; x++) {
				if(!shift) {
					if(userBoard[r][c][x] == v) {
						userBoard[r][c][x] = 0;
						shift = true;
					}
				} else {
					userBoard[r][c][x] = userBoard[r][c][x+1];
				}
			}
			userBoard[r][c][9] = 0;
		}
	}
	
	public int getVal(int r, int c, int z) {
		return userBoard[r][c][z];
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
		/*for(int i = 0; i<9; i++) {
			for(int j = 0; j<9;j ++) {
				System.out.print(completedBoard[i][j][0]);
			}
			System.out.println();
		}*/
		
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
