package com.whitehat.chess.exceptions;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException() {
        super("Specified move is invalid");
    }
}
