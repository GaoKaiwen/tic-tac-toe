package server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player {

    private char symbol;
    private Socket socket;
    private BufferedReader bf;
    private BufferedWriter bw;

    public Player(char symbol, Socket socket) throws IOException {
        this.symbol = symbol;
        this.socket = socket;
        bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public boolean playerTurn(Board board, Scanner scan) {

        int position = scan.nextInt();

        switch (position) {
            case 1:
                if(board.getSymbol(0, 0) == ' ') {
                    board.replaceSymbol(0, 0, symbol);
                    return true;
                }
                break;
            case 2:
                if(board.getSymbol(0, 1) == ' ') {
                    board.replaceSymbol(0, 1, symbol);
                    return true;
                }
                break;
            case 3:
                if(board.getSymbol(0, 2) == ' ') {
                    board.replaceSymbol(0, 2, symbol);
                    return true;
                }
                break;
            case 4:
                if(board.getSymbol(1, 0) == ' ') {
                    board.replaceSymbol(1, 0, symbol);
                    return true;
                }
                break;
            case 5:
                if(board.getSymbol(1, 1) == ' ') {
                    board.replaceSymbol(1, 1, symbol);
                    return true;
                }
                break;
            case 6:
                if(board.getSymbol(1, 2) == ' ') {
                    board.replaceSymbol(1, 2, symbol);
                    return true;
                }
                break;
            case 7:
                if(board.getSymbol(2, 0) == ' ') {
                    board.replaceSymbol(2, 0, symbol);
                    return true;
                }
                break;
            case 8:
                if(board.getSymbol(2, 1) == ' ') {
                    board.replaceSymbol(2, 1, symbol);
                    return true;
                }
                break;
            case 9:
                if(board.getSymbol(2, 2) == ' ') {
                    board.replaceSymbol(2, 2, symbol);
                    return true;
                }
                break;
            default:
                System.out.println("Type a valid position!");
                return false;
        }
        System.out.println("This position has already been chosen!");
        return false;
    }

    public char getSymbol() {
        return symbol;
    }

    public void sendCommand(String command) {
        try {
            bw.write(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
