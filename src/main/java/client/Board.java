package client;

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

    public void placeSymbolInBoard(String pos, String symbol) {
        char symbolInChar = symbol.charAt(0);
        switch(pos) {
            case "1" :
                board[0][0] = symbolInChar;
                break;
            case "2" :
                board[0][1] = symbolInChar;
                break;
            case "3" :
                board[0][2] = symbolInChar;
                break;
            case "4" :
                board[1][0] = symbolInChar;
                break;
            case "5" :
                board[1][1] = symbolInChar;
                break;
            case "6" :
                board[1][2] = symbolInChar;
                break;
            case "7" :
                board[2][0] = symbolInChar;
                break;
            case "8" :
                board[2][1] = symbolInChar;
                break;
            case "9" :
                board[2][2] = symbolInChar;
                break;
        }
    }

}
