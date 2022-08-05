package httpserver.server;

import httpserver.interfaces.ISocket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SocketMock implements ISocket {

    public boolean connectionClosed = false;
    public InputStream reader;
    public OutputStream writer;
    public String dataSent;
    public  String receivedData;


    public SocketMock(InputStream reader, OutputStream output) {
        this.reader = reader;
        this.writer = output;
    }

    @Override
    public String receiveData() {
        try {
            StringBuilder clientData = new StringBuilder();
            while(reader.available() > 0){
                clientData.append((char) reader.read());
            }
            receivedData = clientData.toString();
            dataSent = receivedData;
            return receivedData;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public void sendData(String message) throws IOException {
        dataSent = receivedData;
        writer.write(Integer.parseInt(message));
    }

    @Override
    public void close() {
        connectionClosed = true;
    }

    public boolean isConnectionClosed() {
        return connectionClosed;
    }
}
