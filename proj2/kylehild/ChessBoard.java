package eecs285.proj2.kylehild;

public class ChessBoard{
    
    ChessPiece [][] board;
    
    ChessBoard(){
        board = new ChessPiece[8][8];
    }
    
    void initialize(){
        ChessPiece cp;
        
        //set initial position of each piece
        cp = new Rook('w', 0, 0);
        placePiece(cp, 0, 0);
        cp = new Knight('w', 0, 1);
        placePiece(cp, 0, 1);
        cp = new Bishop('w', 0, 2);
        placePiece(cp, 0, 2);
        cp = new Queen('w', 0, 3);
        placePiece(cp, 0, 3);
        cp = new King('w', 0, 4);
        placePiece(cp, 0, 4);
        cp = new Bishop('w', 0, 5);
        placePiece(cp, 0, 5);
        cp = new Knight('w', 0, 6);
        placePiece(cp, 0, 6);
        cp = new Rook('w', 0, 7);
        placePiece(cp, 0, 7);
        for(int i = 0; i < 8; i++){
            cp = new Pawn('w', 1, i);
            placePiece(cp, 1, i);
        }
        cp = new Rook('b', 7, 0);
        placePiece(cp, 7, 0);
        cp = new Knight('b', 7, 1);
        placePiece(cp, 7, 1);
        cp = new Bishop('b', 7, 2);
        placePiece(cp, 7, 2);
        cp = new Queen('b', 7, 3);
        placePiece(cp, 7, 3);
        cp = new King('b', 7, 4);
        placePiece(cp, 7, 4);
        cp = new Bishop('b', 7, 5);
        placePiece(cp, 7, 5);
        cp = new Knight('b', 7, 6);
        placePiece(cp, 7, 6);
        cp = new Rook('b', 7, 7);
        placePiece(cp, 7, 7);
        for(int i = 0; i < 8; i++){
            cp = new Pawn('b', 6, i);
            placePiece(cp, 6, i);
        }
        
        //place blank pieces everywhere else
        for(int i = 2; i < 6; i++)
            for(int j = 0; j < 8; j++){
                cp = new Blank(' ', i, j);
                placePiece(cp, i, j);
            }
    }
    
    ChessPiece getPieceAt(final int row, final int col){
        if(row >= 0 && row <= 7 && col >= 0 && col <= 7){
            return board[row][col];
        }
        else System.out.println("Error: getPieceAt - Invalid space RC: "+row+" "+col);
        return null;
    }
    
    void print(){ //print as specified
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("8 |" + board[7][0] + "|" + board[7][1] + "|" + board[7][2] + 
                             "|" + board[7][3] + "|" + board[7][4] + "|" + board[7][5] + 
                             "|" + board[7][6] + "|" + board[7][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("7 |" + board[6][0] + "|" + board[6][1] + "|" + board[6][2] + 
                             "|" + board[6][3] + "|" + board[6][4] + "|" + board[6][5] + 
                             "|" + board[6][6] + "|" + board[6][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("6 |" + board[5][0] + "|" + board[5][1] + "|" + board[5][2] + 
                             "|" + board[5][3] + "|" + board[5][4] + "|" + board[5][5] + 
                             "|" + board[5][6] + "|" + board[5][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("5 |" + board[4][0] + "|" + board[4][1] + "|" + board[4][2] + 
                             "|" + board[4][3] + "|" + board[4][4] + "|" + board[4][5] + 
                             "|" + board[4][6] + "|" + board[4][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("4 |" + board[3][0] + "|" + board[3][1] + "|" + board[3][2] + 
                             "|" + board[3][3] + "|" + board[3][4] + "|" + board[3][5] + 
                             "|" + board[3][6] + "|" + board[3][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("3 |" + board[2][0] + "|" + board[2][1] + "|" + board[2][2] + 
                             "|" + board[2][3] + "|" + board[2][4] + "|" + board[2][5] + 
                             "|" + board[2][6] + "|" + board[2][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("2 |" + board[1][0] + "|" + board[1][1] + "|" + board[1][2] + 
                             "|" + board[1][3] + "|" + board[1][4] + "|" + board[1][5] + 
                             "|" + board[1][6] + "|" + board[1][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("1 |" + board[0][0] + "|" + board[0][1] + "|" + board[0][2] + 
                             "|" + board[0][3] + "|" + board[0][4] + "|" + board[0][5] + 
                             "|" + board[0][6] + "|" + board[0][7] + "|");
        System.out.println("  |--|--|--|--|--|--|--|--|");
        System.out.println("   a  b  c  d  e  f  g  h");
    }
    
    public void removePiece(final int rowNum, final int colNum){
        //inside bounds then place blank piece in place
        if(rowNum >= 0 && rowNum <= 7 && colNum >= 0 && colNum <= 7){
            ChessPiece cp;
            cp = new Blank(' ', rowNum, colNum);
            board[rowNum][colNum] = cp;
        }
        else{
            System.out.println("Error: removePiece - Invalid space RC: "+rowNum+" "+colNum);
        }
    }
    
    public void placePiece(final ChessPiece pieceToPlace, final int rowNum, final int colNum){
        //in bounds then place new piece and set its new position
        if(rowNum >= 0 && rowNum <= 7 && colNum >= 0 && colNum <= 7){
            board[rowNum][colNum] = pieceToPlace;
            pieceToPlace.setPos(rowNum, colNum);
        }
        else{
            System.out.println("Error: placePiece - Invalid space RC: "+rowNum+" "+colNum);
        }
    }
}
