package httpserver.interfaces;



public interface ISocket {
    String receiveData();

    void sendData(String data);

    void close();
}
