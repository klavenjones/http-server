package httpserver.server;

import httpserver.interfaces.ISocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SocketMock implements ISocket {

    public boolean connectionClosed = false;
    public BufferedReader reader;
    public PrintWriter writer;
    public String dataSent;
    public  String receivedData;


    public SocketMock(BufferedReader input, PrintWriter output) {
        this.reader = input;
        this.writer = output;
    }

    @Override
    public String receiveData() {
        try {
            receivedData = reader.readLine();
            dataSent = receivedData;
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error with Input");
        }
        return null;
    }

    @Override
    public void sendData(String message) {
        dataSent = receivedData;
        writer.write(message);
    }

    @Override
    public void close() {
        connectionClosed = true;
    }

    public boolean isConnectionClosed() {
        return connectionClosed;
    }
}
