package client;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientTest {

    @InjectMocks
    private Client client;

    @Mock
    private SocketMock socket;

    @Mock
    private Scanner scanner;

    @Spy
    private Board board;

    @Captor
    private ArgumentCaptor<String> posCaptor, symbolCaptor;

    @Test
    void testRun() throws IOException {
        socket = createSocketMock();
        scanner = createScanner();

        client.run();

        verify(board, times(7)).placeSymbolInBoard(posCaptor.capture(), symbolCaptor.capture());

        assertEquals("1", posCaptor.getAllValues().get(0));
        assertEquals("2", posCaptor.getAllValues().get(1));
        assertEquals("3", posCaptor.getAllValues().get(2));
        assertEquals("4", posCaptor.getAllValues().get(3));
        assertEquals("5", posCaptor.getAllValues().get(4));
        assertEquals("6", posCaptor.getAllValues().get(5));
        assertEquals("7", posCaptor.getAllValues().get(6));

        assertEquals("X", symbolCaptor.getAllValues().get(0));
        assertEquals("O", symbolCaptor.getAllValues().get(1));
        assertEquals("X", symbolCaptor.getAllValues().get(2));
        assertEquals("O", symbolCaptor.getAllValues().get(3));
        assertEquals("X", symbolCaptor.getAllValues().get(4));
        assertEquals("O", symbolCaptor.getAllValues().get(5));
        assertEquals("X", symbolCaptor.getAllValues().get(6));
    }

    @Test
    void testPlaceSymbolInBoard() throws IOException {
        SocketMock socket = createSocketMock();
        Scanner scanner = createScanner();
        Board board = mock(Board.class);

        client.run();

//        verify(client).printBoard();
    }
    private String convertStringArrayToSingleString(String[] strings) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for(int i = 0; i < strings.length; i++) {
            stringJoiner.add(strings[i]);
        }
        return stringJoiner.toString();
    }

    private SocketMock createSocketMock() {
        String[] socketInputCommands = {
                "X",
                "valid",
                "continue",
                "continue",
                "2",
                "valid",
                "continue",
                "continue",
                "4",
                "valid",
                "continue",
                "continue",
                "6",
                "valid",
                "win"
        };
        SocketMock socket = new SocketMock();
        socket.setServerMessages(convertStringArrayToSingleString(socketInputCommands));
        return socket;
    }

    private Scanner createScanner() {
        String[] scannerCommands = {
                "1",
                "3",
                "5",
                "7"
        };
        return new Scanner(new ByteArrayInputStream(convertStringArrayToSingleString(scannerCommands).getBytes()));
    }
}