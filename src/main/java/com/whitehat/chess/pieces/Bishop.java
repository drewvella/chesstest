package com.whitehat.chess.pieces;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean validateMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
        return isDiagonal(xFrom, yFrom, xTo, yTo);
    }

}
