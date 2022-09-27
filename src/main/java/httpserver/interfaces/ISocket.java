package httpserver.interfaces;


import java.io.IOException;

public interface ISocket {
    String receiveData();

    void sendData(byte[] data) throws IOException;

    void close();
}
