package eecs285.proj2.kylehild;

public class King extends ChessPiece {
    
    King(char c, int r, int cl){
        super(c, r, cl);
    }
    
    public int getNumberOfMoves(final ChessBoard board, final boolean printMoves){
        int numMoves = 0;

        if(printMoves) System.out.println(color + "K at " + getColChar(col) + (row+1) + " valid moves:");

        numMoves += CheckBoardAt(board, row+1, col, printMoves);
        numMoves += CheckBoardAt(board, row+1, col+1, printMoves);
        numMoves += CheckBoardAt(board, row, col+1, printMoves);
        numMoves += CheckBoardAt(board, row-1, col+1, printMoves);
        numMoves += CheckBoardAt(board, row-1, col, printMoves);
        numMoves += CheckBoardAt(board, row-1, col-1, printMoves);
        numMoves += CheckBoardAt(board, row, col-1, printMoves);
        numMoves += CheckBoardAt(board, row+1, col-1, printMoves);

        if(printMoves) System.out.println("  Total Possible Moves: " + numMoves);

        return numMoves;
    }
    
    public String toString(){
        String s = "";
        s += color + "K";
        return s;
    }
}
