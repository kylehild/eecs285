package eecs285.proj2.kylehild;

public class Blank extends ChessPiece {
    
    Blank(char c, int r, int cl){
        super(c, r, cl);
    }
    
    public int getNumberOfMoves(final ChessBoard board, final boolean printMoves){
        System.out.println("Error: getNumberOfMoves - Invalid piece at space RC: "+row+" "+col);
        throw new RuntimeException();
    }
    
    public String toString(){
        return "  ";
    }
}
