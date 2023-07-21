package server;

public class Board {

    private char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public void printBoard() {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-+");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-+");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

    public char getSymbol(int x, int y) {
        return board[x][y];
    }

    public int getLength() {
        return board.length;
    }

    public void replaceSymbol(int x, int y, char symbol) {
        board[x][y] = symbol;
    }

}
