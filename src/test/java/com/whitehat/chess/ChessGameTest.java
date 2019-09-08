package com.whitehat.chess;

import com.whitehat.chess.exceptions.CheckException;
import com.whitehat.chess.exceptions.IllegalMoveException;
import com.whitehat.chess.exceptions.InvalidMoveException;
import com.whitehatgaming.UserInputFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class ChessGameTest {



    @Test(expected = CheckException.class)
    public void testForCheck() {
        executeMoves("checkMate.txt");
    }

    @Test
    public void testForNoExceptions() {
        executeMoves("sample-moves.txt");
    }

    @Test(expected = InvalidMoveException.class)
    public void testForInvalidMove() {
        executeMoves("sample-moves-invalid.txt");
    }

    @Test(expected = IllegalMoveException.class)
    public void testForIllegalMove() {
        executeMoves("sample-moves-illegal.txt");
    }


    private void executeMoves(String fileName) {
        Board board = new Board();
        try {
            UserInputFile userInputFile = new UserInputFile(fileName);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
