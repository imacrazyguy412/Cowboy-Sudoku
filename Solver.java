public class Solver {
	int[][][] board = new int[9][9][10];
	int[][] given;
	int temp;
	boolean possible;
	int attempts = 0;
	
	//The "main" method for the solver, this pieces everything together and tells other methods to solve the board
	public boolean solve (int[][] given) {
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				for(int d = 0; d < 10; d++) {
					board[r][c][d] = 0;
				}
			}
		}
		possible = false;
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				board[r][c][0] = given[r][c];
			}
		}
		for(int a = 0; a < 50; a++) {
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					if(!(given[r][c] == 0) && a == 0) {
					board[r][c][0] = given[r][c];
					} else if(a > 0) {
						board = ruleOutRowColSquare(given, board, c, r);
						if(!(board[r][c][0] == 0)) {
							for(int b = 1; b < 10; b++) {
								board[r][c][b] = 0;
							}
						}
						if(board[r][c][2] == 0 && (!(board[r][c][1] == 0)) && (board[r][c][0] == 0)) {
							board[r][c][0] = board[r][c][1];
							board[r][c][1] = 0;
						}
						for(int d = 1; d < 9; d++) {
							temp = board[r][c][d];
							if(!(temp == 0)) {
								board = mustBeHere(given, board, r, c, temp);
							}
						}
						board = tryRow(board, r);
						board = tryCol(board, c);
						//board = trySquare(board, r, c);
					}
				}
			}
			temp = 0;
			possible = true;
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					if(board[r][c][0] == 0) {
						possible = false;
						temp++;
					}
				}
			}
			if(temp == 0) {
				attempts = a;
				return possible;
			}
			temp = 0;
			
		}
		return possible;
	}
	
	//A get board if needed
	public int[][][] getBoard() {
		return board;
	}
	
	//Returns the number of loops it took for the board to be solved
	public int getAttempts() {
		return attempts;
	}
	
	//Looks at a given spot and attempts to either find a solution or rule out any impossible numbers and set the potential numbers to the appropriate options
	public int[][][] ruleOutRowColSquare(int[][] givenBoard, int[][][] board, int cOrigin, int rOrigin) {
		int[] impossibleVals = new int[999];
		int squarec = cOrigin/3;
		int squarer = rOrigin/3;
		int counter = 0;
		int possibleValsCounter = 1;
		boolean include;
		for(int c = 0; c < 9; c++) {
			if(!(c == cOrigin)) {
				if(!(givenBoard[rOrigin][c] == 0)) {
					impossibleVals[counter] = givenBoard[rOrigin][c];
					counter++;
				}
			}
		}
		for(int r = 0; r < 9; r++) {
			if(!(r == rOrigin)) {
				if(!(givenBoard[r][cOrigin] == 0)) {
					impossibleVals[counter] = givenBoard[r][cOrigin];
					counter++;
				}
			}
		}
		for(int g = squarec*3; g < squarec*3+3; g++) {
			for(int h = squarer*3; h< squarer*3+3; h++) {
				if(!((g == cOrigin)&&(h == rOrigin))) {
					if(!(givenBoard[h][g] == 0)) {
						impossibleVals[counter] = givenBoard[h][g];
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
				board[rOrigin][cOrigin][possibleValsCounter] = i;
				possibleValsCounter++;
			}
		}
		return board;
	}
	
	//Looks through the other rows and columns in a given spot and tries to determine if they all contain a specific number, if they all do, then the number it was looking for must be in the given spot
	public int[][][] mustBeHere(int[][] givenBoard, int[][][] board, int rOrigin, int cOrigin, int num) {
		int squarec = cOrigin/3;
		int squarer = rOrigin/3;
		int counter = 0;
		if(board[rOrigin][cOrigin][0] == 0) {
			for(int r = 0; r < 9; r++) {
				for(int g = squarec*3; g < squarec*3+3; g++) {
					if(!(g == cOrigin)) {
						if(givenBoard[r][g] == num) {
							counter++;
						}
					}
				}
			}
			
			for(int h = squarer*3; h< squarer*3+3; h++) {
				for(int c = 0; c < 9; c++) {
					if(!(h == rOrigin)) {
						if(givenBoard[h][c] == num) {
							counter++;
						}
					}
				}
			}
			if(counter < 4) {
				return board;
			}
			board[rOrigin][cOrigin][0] = num;
			for(int x = 1; x < 10; x++) {
				board[rOrigin][cOrigin][x] = 0;
			}
		}
		return board;
	}
	
	//Attempts to complete a full given row, or at least part of it and rule out impossible potential numbers
	public int[][][] tryRow(int[][][] board, int row) {
		int[] missingnums = new int[9];
		int arrcounter = 0;
		int counter = 0;
		
		for(int a = 1; a < 10; a++) {
			for(int b = 0; b < 9; b++) {
				if(!(board[row][b][0] == a)) {
					counter++;
				}
				if(counter == 9) {
					missingnums[arrcounter] = a;
					arrcounter++;
				}
			}
			counter = 0;
		}
		
		for(int a = 0; a < arrcounter; a++) {
			for(int c = 0; c < 9; c++) {
				if(board[row][c][0] == 0) {
					if(canFit(board, missingnums[a], row, c)) {
						counter++;
					} else {
						board = removePotentialNum(board, row, c, missingnums[a]);
					}
				}
			}
			
			if(counter == 1) {
				for(int c = 0; c < 9; c++) {
					if(board[row][c][0] == 0) {
						if(canFit(board, missingnums[a], row, c)) {
							board[row][c][0] = missingnums[a];
							for(int d = 1; d < 9; d++) {
								board[row][c][d] = 0;
							}
						}
					}
				}
			}
			counter = 0;
		}
		return board;
		
	}
	
	//Attempts to complete a full given column, or at least part of it and rule out impossible potential numbers
	public int[][][] tryCol(int[][][] board, int col) {
		int[] missingnums = new int[9];
		int arrcounter = 0;
		int counter = 0;
		
		for(int a = 1; a < 10; a++) {
			for(int b = 0; b < 9; b++) {
				if(!(board[b][col][0] == a)) {
					counter++;
				}
				if(counter == 9) {
					missingnums[arrcounter] = a;
					arrcounter++;
				}
			}
			counter = 0;
		}
		
		for(int a = 0; a < arrcounter; a++) {
			for(int r = 0; r < 9; r++) {
				if(board[r][col][0] == 0) {
					if(canFit(board, missingnums[a], r, col)) {
						counter++;
					} else {
						board = removePotentialNum(board, r, col, missingnums[a]);
					}
				}
			}
			
			if(counter == 1) {
				for(int r = 0; r < 9; r++) {
					if(board[r][col][0] == 0) {
						if(canFit(board, missingnums[a], r, col)) {
							board[r][col][0] = missingnums[a];
							for(int d = 1; d < 9; d++) {
								board[r][col][d] = 0;
							}
						}
					}
				}
			}
			counter = 0;
		}
		return board;
	}
	
	//Attempts to complete a full given 3x3 square, or at least part of it and rule out impossible potential numbers. VERY BUGGY, NEEDS IMPROVEMENT BUT CODE SEEMS TO WORK BETTER WITHOUT IT
	/*public int[][][] trySquare(int[][][] board, int row, int col) {
		int squarer = row/3;
		int squarec = col/3;
		int[] missingnums = new int[9];
		int arrcounter = 0;
		int counter = 0;
		
		for(int a = 1; a < 10; a++) {
			for(int b = squarer*3; b < squarer*3+3; b++) {
				for(int c = squarec*3; c < squarec*3+3; c++) {
					if(!(board[b][c][0] == a)) {
						counter++;
					}
					if(counter == 9) {
						missingnums[arrcounter] = a;
						arrcounter++;
					}
				}
			}
			counter = 0;
		}
		
		for(int a = 0; a < arrcounter; a++) {
			for(int b = squarer*3; b < squarer*3+3; b++) {
				for(int c = squarec*3; c < squarec*3+3; c++) {
					if(board[row][c][0] == 0) {
						if(canFit(board, missingnums[a], b, c)) {
							counter++;
						} else {
							board = removePotentialNum(board, b, c, missingnums[a]);
						}
					}
				}
			}
			
			if(counter == 1) {
				for(int b = squarer*3; b < squarer*3+3; b++) {
					for(int c = squarec*3; c < squarec*3+3; c++) {
						if(board[b][c][0] == 0) {
							if(canFit(board, missingnums[a], b, c)) {
								board[b][c][0] = missingnums[a];
								for(int d = 1; d < 9; d++) {
									board[b][c][d] = 0;
								}
							}
						}
					}
				}
			}
			counter = 0;
		}
		return board;
	}*/
	
	//A method that will try complicated sudoku strategies, UNFINISHED, UNTESTED, MAY BE DISCARDED
	/*public int[][][] tryComplicatedShenanigans(int[][][] board, int rOrigin, int cOrigin) {
		int squarec = cOrigin/3;
		int squarer = rOrigin/3;
		int[] missingnums = new int[9];
		int arrcounter = 0;
		int counter = 0;
		
		for(int a = 1; a < 10; a++) {
			for(int b = squarer*3; b < squarer*3+3; b++) {
				for(int c = squarec*3; c < squarec*3+3; c++) {
					if(!(b == rOrigin)) {
						if(!(board[b][c][0] == a)) {
							counter++;
						}
						if(counter == 6) {
							missingnums[arrcounter] = a;
							arrcounter++;
						}
					}
				}
			}
			counter = 0;
		}
		
		return board;
	}*/
	
	//Takes in a number and a spot and checks if that number can fit in that spot
	public boolean canFit(int[][][] board, int val, int rOrigin, int cOrigin) {
		int squarec = cOrigin/3;
		int squarer = rOrigin/3;
		for(int c = 0; c < 9; c++) {
			if(!(c == cOrigin)) {
				if((board[rOrigin][c][0] == val)) {
					return false;
				}
			}
		}
		for(int r = 0; r < 9; r++) {
			if(!(r == rOrigin)) {
				if((board[r][cOrigin][0] == val)) {
					return false;
				}
			}
		}
		for(int g = squarer*3; g < squarer*3+3; g++) {
			for(int h = squarec*3; h< squarec*3+3; h++) {
				if(!(g == rOrigin)&& !(h == cOrigin)) {
					if((board[g][h][0] == val)) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	//clears a given potential number from a given spot
	public int[][][] removePotentialNum(int[][][] board, int row, int col, int num) {
		boolean shift = false;
		for(int x = 1; x < 8; x++) {
			if(shift) {
				board[row][col][x] = board[row][col][x+1];
			} else {
				if(board[row][col][x] == num) {
					shift = true;
					board[row][col][x] = 0;
				}
			}
		}
		board[row][col][8] = 0;
		return board;
	}
	
	//Checks all potential numbers on a given spot, and removes them if they are invalid
	public int[][][] clearInvalidPotentials(int[][][] board, int row, int col) {
		int shift = 0;
		for(int x = 1; x < 8; x++) {
			board[row][col][x] = board[row][col][x+shift];
			if(!canFit(board, board[row][col][x], row, col)) {
				shift++;
				board[row][col][x] = 0;
			}
			
		}
		return board;
	}
	
	
	
	
	//Used for generating a board, checks if a number can fit in a given spot
	public boolean valid(int[][] board, int val, int cOrigin, int rOrigin) {
		int squarec = cOrigin/3;
		int squarer = rOrigin/3;
		int counter = 0;
		int[] impossiblevals = new int[999];
		for(int c = 0; c < 9; c++) {
			if(!(c == cOrigin)) {
				if((board[c][rOrigin] == val)) {
					impossiblevals[counter] = board[c][rOrigin];
					counter++;
				}
			}
		}
		for(int r = 0; r < 9; r++) {
			if(!(r == rOrigin)) {
				if((board[cOrigin][r] == val)) {
					impossiblevals[counter] = board[cOrigin][r];
					counter++;
				}
			}
		}
		for(int g = squarer*3; g < squarer*3+3; g++) {
			for(int h = squarec*3; h< squarec*3+3; h++) {
				if(!((g == rOrigin)&&(h == cOrigin))) {
					if((board[h][g] == val)) {
						impossiblevals[counter] = board[h][g];
						counter++;
					}
				}
			}
		}
		for(int c = 0; c < counter; c++) {
			if(impossiblevals[c] == val) {
				return false;
			}
		}
		return true;
	}
	
	//Used for generating a board, checks if a given spot contains all numbers 1-9
	public boolean notFull(int[][] board, int cOrigin, int rOrigin) {
		int squarec = cOrigin/3;
		int squarer = rOrigin/3;
		int arrcounter = 0;
		int retcounter = 0;
		int[] impossiblevals = new int[999];
		for(int c = 0; c < 9; c++) {
			if(!(c == cOrigin)) {
				if(!(board[c][rOrigin] == 0)) {
					impossiblevals[arrcounter] = board[c][rOrigin];
					arrcounter++;
				}
			}
		}
		for(int r = 0; r < 9; r++) {
			if(!(r == rOrigin)) {
				if(!(board[cOrigin][r] == 0)) {
					impossiblevals[arrcounter] = board[cOrigin][r];
					arrcounter++;				}
			}
		}
		for(int g = squarer*3; g < squarer*3+3; g++) {
			for(int h = squarec*3; h< squarec*3+3; h++) {
				if(!((g == rOrigin)&&(h == cOrigin))) {
					if(!(board[h][g] == 0)) {
						impossiblevals[arrcounter] = board[h][g];
						arrcounter++;
					}
				}
			}
		}
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < arrcounter; c++) {
				if(impossiblevals[c] == r) {
					retcounter++;
					c = arrcounter;
				}
			}
		}
		if(retcounter == 9) {
			return false;
		}
		return true;
	}
}
