package httpserver.interfaces;

import java.net.ServerSocket;
import java.net.Socket;

public interface IServerSocket {
    ServerSocket createServerSocket(int port);
    Socket acceptConnection();
    void close();
}
