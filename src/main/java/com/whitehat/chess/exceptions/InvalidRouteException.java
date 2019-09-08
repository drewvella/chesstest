package com.whitehat.chess.exceptions;


public class InvalidRouteException extends RuntimeException {
    public InvalidRouteException() {
        super("Specified route is invalid");
    }
}
