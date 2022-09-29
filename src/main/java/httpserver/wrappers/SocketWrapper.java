package httpserver.wrappers;

import httpserver.interfaces.ISocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketWrapper implements ISocket {

    private final Socket socket;
    private final InputStream input;
    private final OutputStream output;


    public SocketWrapper(Socket clientSocket) throws IOException {
        this.socket = clientSocket;
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
    }

    @Override
    public String receiveData() {
        try {
            StringBuilder clientData = new StringBuilder();
            do {
                clientData.append((char) input.read());
            } while (input.available() > 0);
            return clientData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public void sendData(byte[] data) throws IOException {
        output.write(data);
        output.flush();
    }

    @Override
    public void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
