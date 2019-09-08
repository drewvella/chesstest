package com.whitehat.chess;

import lombok.Data;

@Data
public class Move {

    public Move(int[] data) {
        xFrom = data[0];
        yFrom = data[1];
        xTo = data[2];
        yTo = data[3];
    }

    private int xFrom;
    private int yFrom;
    private int xTo;
    private int yTo;
}
