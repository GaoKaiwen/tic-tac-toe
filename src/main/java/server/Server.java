package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Game game;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() throws IOException {
        game = new Game();
        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                if(!game.hasPlayer()) {
                    game.setPlayer1(new Player('X', socket));
                } else if(!game.hasBothPlayers()) {
                    game.setPlayer2(new Player('O', socket));
                } else {
                    game = new Game();
                    game.setPlayer1(new Player('X', socket));
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Server server = new Server(serverSocket);
        server.start();
    }

}
