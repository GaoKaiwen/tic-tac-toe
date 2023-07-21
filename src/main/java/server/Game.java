package server;

import java.util.Scanner;

public class Game implements Runnable {

    private Board board;
    private Player player1;
    private Player player2;

    public Game() {
        board = new Board();
    }

    private boolean isFinished() {
        if((board.getSymbol(0, 0) == 'X' && board.getSymbol(0, 1) == 'X' && board.getSymbol(0, 2) == 'X') ||
                (board.getSymbol(1, 0) == 'X' && board.getSymbol(1, 1) == 'X' && board.getSymbol(1, 2) == 'X') ||
                (board.getSymbol(2, 0) == 'X' && board.getSymbol(2, 1) == 'X' && board.getSymbol(2, 2) == 'X') ||

                (board.getSymbol(0, 0) == 'X' && board.getSymbol(1, 0) == 'X' && board.getSymbol(2, 0) == 'X') ||
                (board.getSymbol(0, 1) == 'X' && board.getSymbol(1, 1) == 'X' && board.getSymbol(2, 1) == 'X') ||
                (board.getSymbol(0, 2) == 'X' && board.getSymbol(1, 2) == 'X' && board.getSymbol(2, 2) == 'X') ||

                (board.getSymbol(0, 0) == 'X' && board.getSymbol(1, 1) == 'X' && board.getSymbol(2, 2) == 'X') ||
                (board.getSymbol(0, 2) == 'X' && board.getSymbol(1, 1) == 'X' && board.getSymbol(2, 0) == 'X')) {
            System.out.println("Player 1 won!");
            return true;
        }

        if((board.getSymbol(0, 0) == 'O' && board.getSymbol(0, 1) == 'O' && board.getSymbol(0, 2) == 'O') ||
                (board.getSymbol(1, 0) == 'O' && board.getSymbol(1, 1) == 'O' && board.getSymbol(1, 2) == 'O') ||
                (board.getSymbol(2, 0) == 'O' && board.getSymbol(2, 1) == 'O' && board.getSymbol(2, 2) == 'O') ||

                (board.getSymbol(0, 0) == 'O' && board.getSymbol(1, 0) == 'O' && board.getSymbol(2, 0) == 'O') ||
                (board.getSymbol(0, 1) == 'O' && board.getSymbol(1, 1) == 'O' && board.getSymbol(2, 1) == 'O') ||
                (board.getSymbol(0, 2) == 'O' && board.getSymbol(1, 2) == 'O' && board.getSymbol(2, 2) == 'O') ||

                (board.getSymbol(0, 0) == 'O' && board.getSymbol(1, 1) == 'O' && board.getSymbol(2, 2) == 'O') ||
                (board.getSymbol(0, 2) == 'O' && board.getSymbol(1, 1) == 'O' && board.getSymbol(2, 0) == 'O')) {
            System.out.println("Player 2 won!");
            return true;
        }

        for(int i = 0; i < board.getLength(); i++) {
            for(int j = 0; j < board.getLength(); j++) {
                if(board.getSymbol(i, j) == ' ')
                    return false;
            }
        }

        System.out.println("It's a tie!");
        return true;
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);

        board.printBoard();

        boolean played;
        Player currentPlayer = player1;

        while(!isFinished()) {
            int playerNumber = "X".equalsIgnoreCase(String.valueOf(currentPlayer.getSymbol())) ? 1 : 2;
            System.out.println("Player " + playerNumber + ", type the number of the position of your choice (1-9): ");
            played = currentPlayer.playerTurn(board, scan);
            while(!played) {
                played = currentPlayer.playerTurn(board, scan);
            }
            board.printBoard();
            if(playerNumber == 1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
    }

    public boolean hasPlayer() {
        return (player1 != null) || (player2 != null);
    }

    public boolean hasBothPlayers() {
        return (player1 != null) && (player2 != null);
    }

    public void setPlayer1(Player player) {
        this.player1 = player;
        player1.sendCommand("1");
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
        player2.sendCommand("2");
    }
}
