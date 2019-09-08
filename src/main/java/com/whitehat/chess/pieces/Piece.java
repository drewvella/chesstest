package com.whitehat.chess.pieces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data

public abstract class Piece {
    private Color color;
    private PieceType pieceType;
    private boolean firstMove = true;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    @Override
    public String toString() {
        String representation = color.getRepresentation() + pieceType.getRepresentation();
        return color == Color.WHITE ? representation.toUpperCase() : representation.toLowerCase();
    }

   public abstract boolean validateMove(int xfrom, int yfrom, int xto, int yto, boolean capture);

    boolean isDiagonal(int xfrom, int yfrom, int xto, int yto) {
        // A move is diagonal if abs(dx) == abs(dy)
        return (Math.abs(xto - xfrom) == Math.abs(yto - yfrom));
    }

    boolean isUpDownLeftRight(int xfrom, int yfrom, int xto, int yto) {
        // Either only the y changes, or only the x changes.
        return ((xfrom == xto && yfrom != yto) ||
                (yfrom == yto && xfrom != xto));
    }

}



@AllArgsConstructor
@Getter
enum PieceType {
    ROOK ("R"),
    KNIGHT ("N"),
    BISHOP ("B"),
    KING ("K"),
    QUEEN ("Q"),
    PAWN ("P");

    private String representation;
}