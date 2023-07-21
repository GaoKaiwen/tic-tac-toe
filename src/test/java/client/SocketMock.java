package client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SocketMock extends Socket {

    private String serverMessages;
    private int msgAux = 0;
    private List<Byte> bytesList = new ArrayList<>();

    public SocketMock() {}

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(serverMessages.getBytes());
    }

    public OutputStream getOutputStream() {
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                bytesList.add((byte) b);
            }
        };
    }

    public String output() {
        byte[] converted = toByteArray(bytesList);
        return new String(converted, StandardCharsets.UTF_8);
    }

    private byte[] toByteArray(List<Byte> byteList) {
        byte[] byteArray = new byte[byteList.size()];
        int index = 0;
        for (byte b : byteList) {
            byteArray[index++] = b;
        }
        return byteArray;
    }

    public void setServerMessages(String serverMessages) {
        this.serverMessages = serverMessages;
    }

}
