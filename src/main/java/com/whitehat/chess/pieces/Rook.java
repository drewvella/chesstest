package com.whitehat.chess.pieces;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean validateMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
        return isUpDownLeftRight(xFrom, yFrom, xTo, yTo);
    }
}
