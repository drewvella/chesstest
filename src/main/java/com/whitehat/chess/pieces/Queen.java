package com.whitehat.chess.pieces;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }


    @Override
   public  boolean validateMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
        // A Queen can move any number of spaces vertically, horizontally,
        // diagonally, in any direction.
        return (isDiagonal(xFrom, yFrom, xTo, yTo) ||
                isUpDownLeftRight(xFrom, yFrom, xTo, yTo));
    }
}
