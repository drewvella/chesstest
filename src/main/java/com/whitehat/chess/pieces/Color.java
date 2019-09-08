package com.whitehat.chess.pieces;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    BLACK("B"),
    WHITE("W");

    private String representation;
}
