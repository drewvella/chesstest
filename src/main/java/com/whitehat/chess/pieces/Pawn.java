package com.whitehat.chess.pieces;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }
    @Override
    public boolean validateMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {

        return (this.validDirection(yFrom, yTo) &&			// Pawns move only forward.
                ((super.isFirstMove()) ?							// Is this the pawn's first move?
                        ((Math.abs(yTo - yFrom) == 2) ||		// Then it can move 2 spaces forward
                                Math.abs(yTo - yFrom) == 1) : 		// or one space
                        (Math.abs(yTo-yFrom) == 1)) &&			// Otherwise just one space
                ((capture && 								// It's a capture,
                        Math.abs(xTo - xFrom) == 1 &&			// And the move is forward and diagonal by 1
                        Math.abs(yTo-yFrom) == 1) ||
                        (!capture && xFrom == xTo)));			// or it's not, and the move is only forward
    }

    private boolean validDirection(int yfrom, int yto) {
        return (this.getColor() == Color.WHITE) ? (yfrom >= yto) : (yto >= yfrom);
    }
}
