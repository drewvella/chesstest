package com.whitehat.chess.exceptions;

public class IllegalMoveException extends RuntimeException {

    public IllegalMoveException() {
        super("Specified move is illegal");
    }
}
