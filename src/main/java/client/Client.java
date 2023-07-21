package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bf;
    private BufferedWriter bw;
    private Scanner scan;
    private Board board;

    public Client(Socket socket, Scanner scan, Board board) throws IOException {
        this.socket = socket;
        bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.scan = scan;
        this.board = board;
    }

    public void run() {
        try {
            // Get player symbol (X or O) from server
            String playerSymbol = bf.readLine();
            String opponentPlayerSymbol = "X".equals(playerSymbol) ? "O" : "X";

            String legalMove = "";
            String opponentPos = "";
            String gameOver = "continue";
            boolean myTurn = "X".equals(playerSymbol);
            while("continue".equals(gameOver)) {
                if(myTurn == false) {
                    // Server sends msg wether game is over after opponents move
                    gameOver = bf.readLine();
                    if("lose".equals(gameOver)) {
                        System.out.println("\nYou LOST! Good luck next time!");
                        break;
                    }
                    if("tie".equals(gameOver)) {
                        System.out.println("\nIt's a TIE!");
                        break;
                    }

                    System.out.println("Wait for your opponent move...");
                    opponentPos = bf.readLine();
                    updateAndPrintBoard(opponentPlayerSymbol, opponentPos);

                    legalMove = "";
                    myTurn = true;
                } else {
                    String pos = "";
                    while(!"valid".equals(legalMove)) {
                        System.out.println("Choose a move: (0-9)");
                        pos = scan.nextLine();
                        bw.write(String.valueOf(pos));
                        legalMove = bf.readLine();
                    }
                    updateAndPrintBoard(playerSymbol, pos);

                    // Server sends msg wether game is over after my move
                    gameOver = bf.readLine();
                    if("win".equals(gameOver)) {
                        System.out.println("\nYou WON! Congratulations!");
                        break;
                    }
                    if("tie".equals(gameOver)) {
                        System.out.println("\nIt's a TIE!");
                        break;
                    }
                    legalMove = "";
                    myTurn = false;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateAndPrintBoard(String opponentPlayerSymbol, String opponentPos) {
        board.placeSymbolInBoard(opponentPos, opponentPlayerSymbol);
        board.printBoard();
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8010);
        Scanner scan = new Scanner(System.in);
        Board board = new Board();
        Client client = new Client(socket, scan, board);
        client.run();
    }

}
