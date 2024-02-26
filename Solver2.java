class Solver2 {
    public  void solveSudoku(char[][] board) {
        solve(0,0, board, 0);
    }
     int solve(int i, int j, char[][] board, int count /*initailly called with 0*/) {
        if (i == 9) {
            i = 0;
            if (++j == 9)
                return 1+count;
        }
        if (board[i][j] != '.')  // skip filled board
            return solve(i+1,j,board, count);
        // search for 2 solutions instead of 1
        // break, if 2 solutions are found
        for (char val = '1'; val <= '9' && count < 2; ++val) {
            if (isValid(val,i,j,board)) {
                board[i][j] = val;
                // add additional solutions
                count = solve(i+1,j,board, count);
            }
        }
        board[i][j] = '.'; // reset on backtrack
        return count;
    }
 

     boolean isValid(char guess, int row, int col, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (guess == board[row][i] || guess == board[i][col]) {
                return false;
            }
        }

        for (int r = (row / 3) * 3; r < (row / 3) * 3 + 3; r++) {
            for (int c = (col / 3) * 3; c < (col / 3) * 3 + 3; c++) {
                if (guess == board[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
    	char[][] board = new char[][] {{'.','.','.','.','.','.','.','.','.'},
			{'6','.','.','1','.','.','.','.','.'},
			{'.','9','8','.','.','.','.','6','.'},
			{'8','.','.','.','6','.','.','.','3'},
			{'.','.','.','8','.','3','.','.','1'},
			{'7','.','.','.','.','.','.','.','6'},
			{'.','6','.','.','.','.','2','8','.'},
			{'.','.','.','4','1','9','.','.','5'},
			{'.','.','.','.','8','.','.','.','.'}};
			
		char[][] board2 = new char[][] {{'7', '.', '.', '.', '.', '.', '2', '.', '.'},  
				{'4', '.', '2', '.', '.', '.', '.', '.', '3'},  
				{'.', '.', '.', '2', '.', '1', '.', '.', '.'},  
				{'3', '.', '.', '1', '8', '.', '.', '9', '7'},  
				{'.', '.', '9', '.', '7', '.', '6', '.', '.'},  
				{'6', '5', '.', '.', '3', '2', '.', '.', '1'},  
				{'.', '.', '.', '4', '.', '9', '.', '.', '.'},  
				{'5', '.', '.', '.', '.', '.', '1', '.', '6'},  
				{'.', '.', '6', '.', '.', '.', '.', '.', '8'}};
			Solver2 solver = new Solver2();
			System.out.println(solver.solve(0,0,board2,0));
			System.out.println();
	}
}