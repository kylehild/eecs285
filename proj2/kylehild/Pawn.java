package eecs285.proj2.kylehild;

public class Pawn extends ChessPiece {
    
    int startRow, startCol;

    Pawn(char c, int r, int cl){
        super(c, r, cl);
        startRow = r;
        startCol = cl;
    }
    
    public int getNumberOfMoves(final ChessBoard board, final boolean printMoves){
        int numMoves = 0;

        if(printMoves) System.out.println(color + "P at " + getColChar(col) + (row+1) + " valid moves:");

        if(color == 'w'){
            //cannot move forward
            if(row == 7) return numMoves;
                
            //place in front vacant
            if(board.getPieceAt(row+1, col).getColor() == ' '){
                numMoves++;
                if(printMoves) System.out.println("   Vacant: " + getColChar(col) + (row+2));

                //still in start position
                if(row == startRow && col == startCol){
                    if(board.getPieceAt(row+2, col).getColor() == ' '){
                        numMoves++;
                        if(printMoves) System.out.println("   Vacant: " + getColChar(col) + (row+3));
                    }
                }
            }

            if(col != 7){
                //diagonal move to take piece
                if(board.getPieceAt(row+1, col+1).getColor() == 'b'){
                    numMoves++;
                    if(printMoves) System.out.println("  Take " + board.getPieceAt(row+1, col+1) + ": " + 
                        getColChar(col+1) + (row+2));
                }
            }
            if(col != 0){
                //diagonal move to take piece
                if(board.getPieceAt(row+1, col-1).getColor() == 'b'){
                    numMoves++;
                    if(printMoves) System.out.println("  Take " + board.getPieceAt(row+1, col-1) + ": " + 
                        getColChar(col-1) + (row+2));
                }
            }
        }
            
        if(color == 'b'){
            //cannot move forward
            if(row == 0) return numMoves;
                
            //place in fornt vacant
            if(board.getPieceAt(row-1, col).getColor() == ' '){
                numMoves++;
                if(printMoves) System.out.println("   Vacant: " + getColChar(col) + row);

                //still in start position
                if(row == startRow && col == startCol){
                    if(board.getPieceAt(row-2, col).getColor() == ' '){
                        numMoves++;
                        if(printMoves) System.out.println("   Vacant: " + getColChar(col) + (row-1));
                    }
                }
            }

            if(col != 7){
                //diagonal move to take piece
                if(board.getPieceAt(row-1, col+1).getColor() == 'w'){
                    numMoves++;
                    if(printMoves) System.out.println("  Take " + board.getPieceAt(row-1, col+1) + ": " + 
                        getColChar(col+1) + row);
                }
            }
            if(col != 0){
                //diagonal move to take piece
                if(board.getPieceAt(row-1, col-1).getColor() == 'w'){
                    numMoves++;
                    if(printMoves) System.out.println("  Take " + board.getPieceAt(row-1, col-1) + ": " + 
                        getColChar(col-1) + row);
                }
            }
        }

        if(printMoves) System.out.println("  Total Possible Moves: " + numMoves);

        return numMoves;
    }
    
    public String toString(){
        String s = "";
        s += color + "P";
        return s;
    }
}
