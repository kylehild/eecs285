package eecs285.proj2.kylehild;

public class Rook extends ChessPiece {
    
    Rook(char c, int r, int cl){
        super(c, r, cl);
    }
    
    public int getNumberOfMoves(final ChessBoard board, final boolean printMoves){
        int numMoves = 0;

        if(printMoves) System.out.println(color + "R at " + getColChar(col) + (row+1) + " valid moves:");

        numMoves += CheckLeft(board, printMoves);
        numMoves += CheckRight(board, printMoves);
        numMoves += CheckUp(board, printMoves);
        numMoves += CheckDown(board, printMoves);

        if(printMoves) System.out.println("  Total Possible Moves: " + numMoves);

        return numMoves;
    }
    
    public String toString(){
        String s = "";
        s += color + "R";
        return s;
    }
}
