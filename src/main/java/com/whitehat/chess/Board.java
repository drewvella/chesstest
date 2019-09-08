package com.whitehat.chess;


import com.whitehat.chess.exceptions.CheckException;
import com.whitehat.chess.exceptions.IllegalMoveException;
import com.whitehat.chess.exceptions.InvalidMoveException;
import com.whitehat.chess.exceptions.InvalidRouteException;
import com.whitehat.chess.pieces.*;

public class Board {

    private Piece[][] positions = new Piece[8][8];

    private String[][] chessboardRepresentation = new String[8][8];


    public Board() {
        init();
        renderBoard();
    }

    /**
     * Initialise the data on the board (i.e. put all pieces into place)
     */
    private void init() {
        //Black pieces
        positions[0][0] = new Rook(Color.BLACK);
        positions[1][0] = new Pawn(Color.BLACK);
        positions[0][1] = new Knight(Color.BLACK);
        positions[1][1] = new Pawn(Color.BLACK);
        positions[0][2] = new Bishop(Color.BLACK);
        positions[1][2] = new Pawn(Color.BLACK);
        positions[0][3] = new Queen(Color.BLACK);
        positions[1][3] = new Pawn(Color.BLACK);
        positions[0][4] = new King(Color.BLACK);
        positions[1][4] = new Pawn(Color.BLACK);
        positions[0][5] = new Bishop(Color.BLACK);
        positions[1][5] = new Pawn(Color.BLACK);
        positions[0][6] = new Knight(Color.BLACK);
        positions[1][6] = new Pawn(Color.BLACK);
        positions[0][7] = new Rook(Color.BLACK);
        positions[1][7] = new Pawn(Color.BLACK);
        //White pieces
        positions[7][0] = new Rook(Color.WHITE);
        positions[6][0] = new Pawn(Color.WHITE);
        positions[7][1] = new Knight(Color.WHITE);
        positions[6][1] = new Pawn(Color.WHITE);
        positions[7][2] = new Bishop(Color.WHITE);
        positions[6][2] = new Pawn(Color.WHITE);
        positions[7][3] = new Queen(Color.WHITE);
        positions[6][3] = new Pawn(Color.WHITE);
        positions[7][4] = new King(Color.WHITE);
        positions[6][4] = new Pawn(Color.WHITE);
        positions[7][5] = new Bishop(Color.WHITE);
        positions[6][5] = new Pawn(Color.WHITE);
        positions[7][6] = new Knight(Color.WHITE);
        positions[6][6] = new Pawn(Color.WHITE);
        positions[7][7] = new Rook(Color.WHITE);
        positions[6][7] = new Pawn(Color.WHITE);

    }


    /**
     * Method to render an ascii representation of the board
     */
    public void renderBoard() {
        chessboardRepresentation = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboardRepresentation[i][j] = positions[i][j] != null ? positions[i][j].toString() : "  ";
            }
        }


        for (int i = 0; i < 8; i++) {

            System.out.println(
                    "|" + chessboardRepresentation[i][0] +
                            "|" + chessboardRepresentation[i][1] +
                            "|" + chessboardRepresentation[i][2] +
                            "|" + chessboardRepresentation[i][3] +
                            "|" + chessboardRepresentation[i][4] +
                            "|" + chessboardRepresentation[i][5] +
                            "|" + chessboardRepresentation[i][6] +
                            "|" + chessboardRepresentation[i][7] + "|"
            );

            System.out.println(
                    "-------------------------");
        }
    }


    public void makeMove(Move move) {
        move(move.getXFrom(), move.getYFrom(), move.getXTo(), move.getYTo());

    }


    private void move(int xfrom, int yfrom, int xto, int yto) {
        Piece from = positions[yfrom][xfrom];
        Piece to = positions[yto][xto];
        boolean capture = (to != null);
        //Validate the move
        validMove(capture, xfrom, yfrom, xto, yto);
        //Set the origin to empty
        positions[yfrom][xfrom] = null;
        //Set the destination to the piece
        positions[yto][xto] = from;
        //Make sure piece is no longer flagged as first move.
        from.setFirstMove(false);
        checkForKingCheck(from.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE);
    }

    private boolean validMove(boolean capture, int xFrom, int yFrom, int xTo, int yTo) {
        Piece from = positions[yFrom][xFrom];
        Piece to = positions[yTo][xTo];

        if (from == null || !from.validateMove(xFrom, yFrom, xTo, yTo, capture)) {
            throw new InvalidMoveException();
        }

        if (to != null && from.getColor() == to.getColor()) {
            throw new IllegalMoveException();
        }

        if (!pathClear(xFrom, yFrom, xTo, yTo)) {
            throw new InvalidRouteException();
        }

        return true;
    }


    private boolean pathClear(int xFrom, int yFrom, int xTo, int yTo) {
        // Returns true if the entire path from the origin to the destination is clear.
        // (This excepts knights, which can move over teammates and enemies.)

        Piece from = positions[yFrom][xFrom];
        Piece to = positions[yTo][xTo];

        // Determine the direction (if any) of x and y movement
        int dx = (xFrom < xTo) ? 1 : ((xFrom == xTo) ? 0 : -1);
        int dy = (yFrom < yTo) ? 1 : ((yFrom == yTo) ? 0 : -1);

        // Determine the number of times we must iterate
        int steps = Math.max(Math.abs(xFrom - xTo), Math.abs(yFrom - yTo));

        if (xFrom == xTo || yFrom == yTo || Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo)) {
            for (int i = 1; i < steps; i++) {
                int x = xFrom + i * dx;
                int y = yFrom + i * dy;
                if (isBlocked(from, to, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBlocked(Piece from, Piece to, int x, int y) {
        return (positions[y][x] != null && positions[y][x] != to && positions[y][x] != from);
    }


    private void checkForKingCheck(Color color) {

        // Get position of the opposite king
        Position position = getKingPosition(color);
        if (position == null) {
            //King can't be found
            throw new CheckException(color == Color.WHITE ? Color.BLACK : Color.WHITE);
        }
        // Iterate over the board and check if a piece of the color received has a legal move to the king
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Piece piece = positions[i][j];
                if (piece != null) {
                    if (piece.getColor() != color && piece.validateMove(j, i, position.getX(), position.getY(), true)) {
                        throw new CheckException(color == Color.WHITE ? Color.BLACK : Color.WHITE);
                    }
                }

            }
        }
    }

    private Position getKingPosition(Color color) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (positions[i][j] instanceof King && positions[i][j].getColor() == color) {
                    return new Position(j, i);
                }
            }
        }
        return null;
    }
}

