import java.util.*;
import java.io.*;

public class CDTTT {
	
	public static ArrayList<char[][]> BigBoard = new ArrayList<char[][]>();
	public static char winning = '*';
	
	public static void main(String[] args) throws FileNotFoundException{
		String inputFileName = "input.txt";
		if (args.length == 1) {
			inputFileName = args[0];
		}
		
		Scanner inp = new Scanner(new File(inputFileName));
		int numTests = inp.nextInt(); inp.nextLine();
		char[][] board1 = null, board2 = null, board3 = null;
		for (int o = 0; o < numTests; o++) {
			
			board1 = setBoard(inp); inp.nextLine();
			board2 = setBoard(inp); inp.nextLine();
			board3 = setBoard(inp);
			
			BigBoard.add(board1); BigBoard.add(board2); BigBoard.add(board3);
			
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (solve(row, col, board1, 0, 1)){
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, 2)) {
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, 3)) {
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, 4)) {
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, 0)) {
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, -1)){
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, -2)){
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, -3)){
						winning = board1[row][col];
						break;
					}
					else if (solve(row, col, board1, 0, -4)){
						winning = board1[row][col];
						break;
					}
				}
			}
			if (winning == 'X') {
				System.out.println("Game #" + (o+1) + ": Winner is Player 1!");
			}
			else if (winning == 'O') {
				System.out.println("Game #" + (o+1) + ": Winner is Player 2!");
			}
			else {
				System.out.println("Game #" + (o+1) + ": Tie!");
			}
			inp.nextLine();
			BigBoard.clear();
			winning = '*';
		}
	}
	
	public static boolean solve(int row, int col, char[][] board, int index, int command) {
		if (board[row][col] == 0 || board[row][col] == '*') {
			return false;
		}
		else if ( (row >= 3 || col >= 3 || row < 0 || col < 0) && board[row][col] == 0 ) {
			return true;
		}
		else if (index >= 2) {
			return true;
		}
		switch(command) {
		case -1:
			if ( board[row][col] == getCheck(row, col-1, BigBoard.get(index+1) ) ){ //Check Horizontal Left
				return solve(row, col-1, BigBoard.get(index+1), index+1, -1);
			}
			break;
		case -2:
			if ( board[row][col] == getCheck(row-1, col, BigBoard.get(index+1) ) ) { //Check Vertical Up
				return solve(row-1, col, BigBoard.get(index+1), index+1, -2);
			}
			break;
		case -3:
			if ( board[row][col] == getCheck(row-1, col-1, BigBoard.get(index+1) ) ) { //Check Diagonal Up-Left
				return solve(row-1, col-1, BigBoard.get(index+1), index+1, -3);
			}
			break;
		case -4:
			if ( board[row][col] == getCheck(row-1, col+1, BigBoard.get(index+1) ) ) { //Check Diagonal Up-Right
				return solve(row-1, col+1, BigBoard.get(index+1), index+1, -4);
			}
			break;
		case 0:
			if ( board[row][col] == getCheck(row, col, BigBoard.get(index+1) ) ) { //Check Stack
				return solve(row, col, BigBoard.get(index+1), index+1, 0);
			}
			break;
		case 1:
			if ( board[row][col] == getCheck(row, col+1, BigBoard.get(index+1) ) ) { //Check Horizontal Right
				return solve(row, col+1, BigBoard.get(index+1), index+1, 1);
			}
			break;
		case 2:
			if ( board[row][col] == getCheck(row+1, col, BigBoard.get(index+1) ) ) { //Check Vertical Down
				return solve(row+1, col, BigBoard.get(index+1), index+1, 2);
			}
			break;
		case 3:
			if ( board[row][col] == getCheck(row+1, col+1, BigBoard.get(index+1) ) ){ //Check Diagonal Down-Right
				return solve(row+1, col+1, BigBoard.get(index+1), index+1, 3);
			}
			break;
		case 4: 
			if ( board[row][col] == getCheck(row+1, col-1, BigBoard.get(index+1) ) ) { //Check Diagonal Down-Left
				return solve(row+1, col-1, BigBoard.get(index+1), index+1, 4);
			}
			break;
		}
		return false;
	}
	
	public static char getCheck(int row, int col, char[][] board) {
		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
			return '*';
		}
		return board[row][col];
	}
	
	public static char[][] setBoard(Scanner ns){
		char[][] board = new char[3][3];
		for (int ix = 0; ix < 3; ix++) {
				String line = ns.nextLine();
			for (int dx = 0; dx < 3; dx++) {
				board[ix][dx] = line.charAt(dx);
			}
		}
		return board;
	}
	
}
