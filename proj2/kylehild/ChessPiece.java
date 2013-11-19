package eecs285.proj2.kylehild;

public abstract class ChessPiece {
    
    char color;
    int row, col;

    public ChessPiece(char c, int r, int cl){
        color = c;
        row = r;
        col = cl;
    }

    //set pieces new pos to inputs
    public void setPos(int r, int c){
        row = r;
        col = c;
    }

    char getColor(){ return color; }

    int CheckBoardAt(ChessBoard board, int rowNum, int colNum, boolean printMoves){
        int numMoves = 0;

        //in bounds
        if(rowNum >= 0 && rowNum < 8 && colNum >= 0 && colNum < 8){
            //Vacant place ahead
            if(board.getPieceAt(rowNum, colNum).getColor() == ' '){
                numMoves++;
                if(printMoves) System.out.println("   Vacant: " + getColChar(colNum) + (rowNum+1));
            }
            //piece to capture ahead
            else if(board.getPieceAt(rowNum, colNum).getColor() != color){
                numMoves++;
                if(printMoves) System.out.println("  Take " + board.getPieceAt(rowNum, colNum) + ": " + 
                        getColChar(colNum) + (rowNum+1));
            }
        }

        return numMoves;
    }

    int CheckLeft(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking To the left
        for(int i = col-1; i >= 0; i--){
            if(board.getPieceAt(row, i).getColor() == color) break;
            numMoves += CheckBoardAt(board, row, i, printMoves);
            if(board.getPieceAt(row, i).getColor() != color &&
                board.getPieceAt(row, i).getColor() != ' ') break;
        }

        return numMoves;
    }

    int CheckRight(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking To the right
        for(int i = col+1; i < 8; i++){
            if(board.getPieceAt(row, i).getColor() == color) break;
            numMoves += CheckBoardAt(board, row, i, printMoves);
            if(board.getPieceAt(row, i).getColor() != color &&
                board.getPieceAt(row, i).getColor() != ' ') break;
        }

        return numMoves;
    }

    int CheckUp(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking Up
        for(int i = row-1; i >= 0; i--){
            if(board.getPieceAt(i, col).getColor() == color) break;
            numMoves += CheckBoardAt(board, i, col, printMoves);
            if(board.getPieceAt(i, col).getColor() != color &&
                board.getPieceAt(i, col).getColor() != ' ') break;
        }

        return numMoves;
    }

    int CheckDown(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking Down
        for(int i = row+1; i < 8; i++){
            if(board.getPieceAt(i, col).getColor() == color) break;
            numMoves += CheckBoardAt(board, i, col, printMoves);
            if(board.getPieceAt(i, col).getColor() != color &&
                board.getPieceAt(i, col).getColor() != ' ') break;
        }

        return numMoves;
    }

    int CheckSE(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking SW
        for(int i = row-1, j = col+1; i >= 0 && j < 8; i--, j++){
            if(board.getPieceAt(i, j).getColor() == color) break;
            numMoves += CheckBoardAt(board, i, j, printMoves);
            if(board.getPieceAt(i, j).getColor() != color &&
                board.getPieceAt(i, j).getColor() != ' ') break;
        }

        return numMoves;
    }

    int CheckNE(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking NE
        for(int i = row+1, j = col+1; i < 8 && j < 8; i++, j++){
            if(board.getPieceAt(i, j).getColor() == color) break;
            numMoves += CheckBoardAt(board, i, j, printMoves);
            if(board.getPieceAt(i, j).getColor() != color &&
                board.getPieceAt(i, j).getColor() != ' ') break;
        }

        return numMoves;
    }

    int CheckNW(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking NW
        for(int i = row+1, j = col-1; i < 8 && j >= 0; i++, j--){
            if(board.getPieceAt(i, j).getColor() == color) break;
            numMoves += CheckBoardAt(board, i, j, printMoves);
            if(board.getPieceAt(i, j).getColor() != color &&
                board.getPieceAt(i, j).getColor() != ' ') break;
        }

        return numMoves;
    }

    int CheckSW(ChessBoard board, boolean printMoves){
        int numMoves = 0;
        
        //Checking SW
        for(int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--){
            if(board.getPieceAt(i, j).getColor() == color) break;
            numMoves += CheckBoardAt(board, i, j, printMoves);
            if(board.getPieceAt(i, j).getColor() != color &&
                board.getPieceAt(i, j).getColor() != ' ') break;
        }

        return numMoves;
    }

    public char getColChar(int colNum){
        //gets letter associated with numerical column
        switch(colNum){
            case 0:     return 'a';
            case 1:     return 'b';
            case 2:     return 'c';
            case 3:     return 'd';
            case 4:     return 'e';
            case 5:     return 'f';
            case 6:     return 'g';
            case 7:     return 'h';
            default:    return 'Z';
        }
    }

    public abstract int getNumberOfMoves(final ChessBoard board, final boolean printMoves);
}
