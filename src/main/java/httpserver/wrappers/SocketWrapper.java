package httpserver.wrappers;

import httpserver.interfaces.ISocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWrapper implements ISocket {

    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;


    public SocketWrapper(Socket clientSocket) throws IOException {
        this.socket = clientSocket;
        this.input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public String receiveData() {
        try {
            String clientInput = input.readLine();
            System.out.println("From the Client " + clientInput);
            return clientInput;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendData(String data) {
        output.println(data);
        System.out.println(" Data to the Client " + data);
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
