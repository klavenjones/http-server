package httpserver.interfaces;


import java.io.IOException;

public interface ISocket {
    String receiveData();

    void sendData(String data) throws IOException;

    void close();
}
