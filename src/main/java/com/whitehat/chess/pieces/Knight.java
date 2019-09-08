package com.whitehat.chess.pieces;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean validateMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
        return ((Math.abs(xFrom - xTo) == 1 && Math.abs(yFrom - yTo) == 2) ||
                (Math.abs(yFrom - yTo) == 1 && Math.abs(xFrom - xTo) == 2));
    }
}
