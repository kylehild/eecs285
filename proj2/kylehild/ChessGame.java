package eecs285.proj2.kylehild;
import static java.lang.System.out;

/** This is an application class that contains a main function,
 *  which will test the interface to the beginnings of a chess
 *  game, as specified by EECS285 programming project #2.
 *  The program takes no interactive or file inputs, and all
 *  output is to the console.
 *  @author  Andrew M. Morgan
 */
public class ChessGame
{
  /** This method is called automatically when the ChessGame class
   *  is interpreted by the JVM.
   */
  public static void main(String [] args)
  {
    ChessBoard board; //Chess board to experiment on
    int numRookMoves; //number of valid moves available to rooks

    board = new ChessBoard();
    board.initialize(); //Set the board to its initial state
    out.println("Printing Initial Board:");
    board.print();      //and print the board to the console

    //Now move some pieces around.
    //NOTE: "Valid moves" are not enforced here.  I've been
    //careful to move these selected pieces in a valid way,
    //but there is no code to allow only valid moves to be
    //made at this point...
    out.println("Moving White Pawn from d2 to d4");
    board.placePiece(board.getPieceAt(1, 3), 3, 3);
    board.removePiece(1, 3);
    out.println("Moving Black Knight from b8 to c6");
    board.placePiece(board.getPieceAt(7, 1), 5, 2);
    board.removePiece(7, 1);
    out.println("Moving White Queen from d1 to d3");
    board.placePiece(board.getPieceAt(0, 3), 2, 3);
    board.removePiece(0, 3);
    out.println("Moving Black Pawn from h7 to h6");
    board.placePiece(board.getPieceAt(6, 7), 5, 7);
    board.removePiece(6, 7);
    out.println("Moving White Pawn from a2 to a3");
    board.placePiece(board.getPieceAt(1, 0), 2, 0);
    board.removePiece(1, 0);
    out.println("Moving Black Pawn from a7 to a6");
    board.placePiece(board.getPieceAt(6, 0), 5, 0);
    board.removePiece(6, 0);
    board.removePiece(6, 8); //This will print an error message
    board.print();

    //Now determine which moves are valid for some misc. pieces.
    board.getPieceAt(1, 2).getNumberOfMoves(board, true); //wP
    board.getPieceAt(6, 6).getNumberOfMoves(board, true); //bP
    board.getPieceAt(0, 0).getNumberOfMoves(board, true); //wR
    board.getPieceAt(7, 7).getNumberOfMoves(board, true); //bR
    board.getPieceAt(0, 1).getNumberOfMoves(board, true); //wN
    board.getPieceAt(6, 2).getNumberOfMoves(board, true); //bP
    board.getPieceAt(2, 3).getNumberOfMoves(board, true); //wQ
    board.getPieceAt(0, 2).getNumberOfMoves(board, true); //wB

    //Determine how many possible moves are valid for the rooks,
    //and print out for each color..
    numRookMoves = board.getPieceAt(0, 0).getNumberOfMoves(board, false);
    numRookMoves += board.getPieceAt(0, 7).getNumberOfMoves(board, false);
    out.println("Num valid moves for white Rooks: " + numRookMoves);

    numRookMoves = board.getPieceAt(7, 0).getNumberOfMoves(board, false);
    numRookMoves += board.getPieceAt(7, 7).getNumberOfMoves(board, false);
    out.println("Num valid moves for black Rooks: " + numRookMoves);
  }
}
