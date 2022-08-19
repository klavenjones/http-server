package httpserver.server;

import httpserver.interfaces.ISocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class SocketMock implements ISocket {

    public boolean connectionClosed = false;
    public InputStream reader;
    public String dataSent;
    public String receivedData;

    public Socket socket;

    public SocketMock(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public String receiveData() {
        try {
            StringBuilder clientData = new StringBuilder();
            while (reader.available() > 0) {
                clientData.append((char) reader.read());
            }
            this.receivedData = clientData.toString();
            return receivedData;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public void sendData(String message) {
        this.dataSent = message;
    }

    @Override
    public void close() {
        connectionClosed = true;
    }

    public boolean isConnectionClosed() {
        return connectionClosed;
    }
}
