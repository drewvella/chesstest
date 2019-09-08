package com.whitehat.chess;

import com.whitehatgaming.UserInputFile;

public class ChessGame {
    public static void main(String[] args) {
        Board board = new Board();
        try {
            UserInputFile userInputFile = new UserInputFile("check-mate.txt");
            int i = 0;
            int[] moveData = userInputFile.nextMove();
            while (moveData != null) {
                System.out.println("Making move" + i);
                board.makeMove(new Move(moveData));
                System.out.println("Board after move" + i);
                board.renderBoard();
                i++;
                moveData = userInputFile.nextMove();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
