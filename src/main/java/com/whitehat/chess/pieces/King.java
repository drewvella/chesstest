package com.whitehat.chess.pieces;

public class King extends Piece {

    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean validateMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
        // A King can move one space in either or both the x and y direction.
        return (Math.abs(xFrom - xTo) <= 1 &&
                Math.abs(yFrom - yTo) <= 1);
    }

}
