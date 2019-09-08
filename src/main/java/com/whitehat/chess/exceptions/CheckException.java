package com.whitehat.chess.exceptions;

import com.whitehat.chess.pieces.Color;

public class CheckException extends RuntimeException {
    public CheckException(Color winner) {
        super("Winner is " + winner);
    }
}
