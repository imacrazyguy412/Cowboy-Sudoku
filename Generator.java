
public class Generator {
	static Solver2 solve = new Solver2();
	
	public static char[][] generate(){
		char[][] board = new char[9][9];
		for(int i =0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				board[i][j] = '.';
			}
		}
		while(solve.solve(0, 0, board, 0) !=1) {
			System.out.println(solve.solve(0, 0,  board, 0));
			int randRow = (int)(Math.random()*9 );
			int randCol = (int)(Math.random()*9 );
			char randNum = (char)('1'+(int)(Math.random()*9));
			while(!solve.isValid(randNum, randRow, randCol,  board)) {
				 randRow = (int)(Math.random()*9 );
				 randCol = (int)(Math.random()*9 );
				 randNum = (char)('1'+(int)(Math.random()*9));
				 
			}
			board[randRow][randCol] = randNum;
			
		}
		System.out.println();
		return board;
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		char[][] board = generate();
		for(int i =0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
