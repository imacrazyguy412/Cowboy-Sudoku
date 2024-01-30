public class Solver {
	int[][][] board = new int[9][9][10];
	int solutions;
	boolean possible;
	
	public boolean solve (int[][] given) {
		possible = false;
		for(int c = 0; c < 100; c++) {
			for(int y = 0; y < 9; y++) {
				for(int x = 0; x < 9; x++) {
					if(!(given[x][y] == 0)) {
						board[x][y][0] = given[x][y];
					} else {
						board = ruleOutRowColSquare(given, board, x, y);
						if(!(board[x][y][0] == 0)) {
							for(int b = 1; b < 10; b++) {
								board[x][y][b] = 0;
							}
						}
						if(board[x][y][2] == 0 && (!(board[x][y][1] == 0)) && (board[x][y][0] == 0)) {
							board[x][y][0] = board[x][y][1];
							board[x][y][1] = 0;
						}
						
					}
				}
			}
			possible = true;
			for(int y = 0; y < 9; y++) {
				for(int x = 0; x < 9; x++) {
					if(board[x][y][0] == 0) {
						possible = false;
					}
				}
			}
		}
		
		return possible;
	}
	
	public int[][][] getBoard() {
		return board;
	}
	
	public int[][][] ruleOutRowColSquare(int[][] givenBoard, int[][][] board, int xOrigin, int yOrigin) {
		int[] impossibleVals = new int[999];
		int squareX = xOrigin/3;
		int squareY = yOrigin/3;
		int counter = 0;
		int possibleValsCounter = 1;
		boolean include;
		for(int x = 0; x < 9; x++) {
			if(!(x == xOrigin)) {
				if(!(givenBoard[x][yOrigin] == 0)) {
					impossibleVals[counter] = givenBoard[x][yOrigin];
					counter++;
				}
			}
		}
		for(int y = 0; y < 9; y++) {
			if(!(y == yOrigin)) {
				if(!(givenBoard[xOrigin][y] == 0)) {
					impossibleVals[counter] = givenBoard[xOrigin][y];
					counter++;
				}
			}
		}
		for(int g = squareY*3; g < squareY*3+3; g++) {
			for(int h = squareX*3; h< squareX*3+3; h++) {
				if(!((g == yOrigin)&&(h == xOrigin))) {
					if(!(givenBoard[g][h] == 0)) {
						impossibleVals[counter] = givenBoard[g][h];
						counter++;
					}
				}
			}
		}
		for(int i = 1; i < 10; i++) {
			include = true;
			for(int j = 0; j < counter; j++) {
				if((impossibleVals[j] == i)) {
					include = false;
				}
			}
			if(include) {
				board[xOrigin][yOrigin][possibleValsCounter] = i;
				possibleValsCounter++;
			}
		}
		return board;
	}
	
	public boolean valid(int[][] board, int val, int xOrigin, int yOrigin) {
		int squareX = xOrigin/3;
		int squareY = yOrigin/3;
		int counter = 0;
		int[] impossiblevals = new int[999];
		for(int x = 0; x < 9; x++) {
			if(!(x == xOrigin)) {
				if((board[x][yOrigin] == val)) {
					impossiblevals[counter] = board[x][yOrigin];
					counter++;
				}
			}
		}
		for(int y = 0; y < 9; y++) {
			if(!(y == yOrigin)) {
				if((board[xOrigin][y] == val)) {
					impossiblevals[counter] = board[xOrigin][y];
					counter++;				}
			}
		}
		for(int g = squareY*3; g < squareY*3+3; g++) {
			for(int h = squareX*3; h< squareX*3+3; h++) {
				if(!((g == yOrigin)&&(h == xOrigin))) {
					if((board[h][g] == val)) {
						impossiblevals[counter] = board[h][g];
						counter++;
					}
				}
			}
		}
		for(int x = 0; x < counter; x++) {
			if(impossiblevals[x] == val) {
				return false;
			}
		}
		return true;
	}
	
	public boolean notFull(int[][] board, int xOrigin, int yOrigin) {
		int squareX = xOrigin/3;
		int squareY = yOrigin/3;
		int arrcounter = 0;
		int retcounter = 0;
		int[] impossiblevals = new int[999];
		for(int x = 0; x < 9; x++) {
			if(!(x == xOrigin)) {
				if(!(board[x][yOrigin] == 0)) {
					impossiblevals[arrcounter] = board[x][yOrigin];
					arrcounter++;
				}
			}
		}
		for(int y = 0; y < 9; y++) {
			if(!(y == yOrigin)) {
				if(!(board[xOrigin][y] == 0)) {
					impossiblevals[arrcounter] = board[xOrigin][y];
					arrcounter++;				}
			}
		}
		for(int g = squareY*3; g < squareY*3+3; g++) {
			for(int h = squareX*3; h< squareX*3+3; h++) {
				if(!((g == yOrigin)&&(h == xOrigin))) {
					if(!(board[h][g] == 0)) {
						impossiblevals[arrcounter] = board[h][g];
						arrcounter++;
					}
				}
			}
		}
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < arrcounter; x++) {
				if(impossiblevals[x] == y) {
					retcounter++;
					x = arrcounter;
				}
			}
		}
		if(retcounter == 9) {
			return false;
		}
		return true;
	}
}
