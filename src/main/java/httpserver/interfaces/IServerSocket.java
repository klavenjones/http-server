package httpserver.interfaces;

import java.net.ServerSocket;
public interface IServerSocket {
    ServerSocket createServerSocket(int port);

    void close();
}
