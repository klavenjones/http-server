package httpserver.wrappers;

import httpserver.interfaces.IServerSocket;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketWrapper implements IServerSocket {

    ServerSocket serverSocket;

    @Override
    public ServerSocket createServerSocket(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            return serverSocket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
