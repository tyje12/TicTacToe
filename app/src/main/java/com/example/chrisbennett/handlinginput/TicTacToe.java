package com.example.chrisbennett.handlinginput;

/**
 * Created by chris.bennett on 10/18/16.
 */
public class TicTacToe {

    public static final int STOP = 0;
    public static final int START = 1;
    public static final int EMPTY = 0;
    public static final int X = 1;
    public static final int O = 2;

    private int mode;
    private int [] board;
    private int turn;

    public TicTacToe() {
        turn = X;
        mode = START;
        board = new int [9];
        //initBoard();

    }

    public boolean makeMove(int cell) {
        if(board[cell]==EMPTY) {
            board[cell] = turn;
            turn = turn % 2 +1;
            return true;
        }
        else {
            return false;
        }
    }

    public int checkForWin() {
        if(board[0]==board[1] && board[1]==board[2] && board[0] != EMPTY) {
            mode=STOP;
            return board[0];
        }
        else if(board[3]==board[4] && board[4]==board[5] && board[3] != EMPTY) {
            mode=STOP;
            return board[3];
        }
        else if(board[6]==board[7] && board[7]==board[8] && board[6] != EMPTY) {
            mode=STOP;
            return board[6];
        }
        else if(board[0]==board[3] && board[3]==board[6] && board[0] != EMPTY) {
            mode=STOP;
            return board[0];
        }
        else if(board[1]==board[4] && board[4]==board[7] && board[1] != EMPTY) {
            mode=STOP;
            return board[1];
        }
        else if(board[2]==board[5] && board[5]==board[8] && board[2] != EMPTY) {
            mode=STOP;
            return board[2];
        }
        else if(board[0]==board[4] && board[4]==board[8] && board[0] != EMPTY) {
            mode=STOP;
            return board[0];
        }
        else if(board[2]==board[4] && board[4]==board[6] && board[2] != EMPTY) {
            mode=STOP;
            return board[2];
        }
        for(int i=0;i<board.length;i++) {
            if (board[i] == EMPTY) {
                return EMPTY;
            }
        }
        mode=STOP;
        return 3;

    }

    public int getMode() {
        return mode;
    }
    public int getCell(int i) {
        return board[i];
    }

    public String toString() {
        String s = "";
        for(int i=0;i<board.length;i++) {
            if(board[i]==X) {
                s+="X";
            }
            else if(board[i]==O){
                s+="O";
            }
            else {
                s+="-";
            }
        }
        return s;
    }


}
