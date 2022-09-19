package httpserver.server;


import httpserver.interfaces.IServerSocket;
import httpserver.wrappers.ServerSocketWrapper;
import httpserver.wrappers.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private static IServerSocket serverSocketWrapper;

    public static ServerSocket buildServerSocket(int portNumber) {
        serverSocketWrapper = new ServerSocketWrapper();
        return serverSocketWrapper.createServerSocket(portNumber);
    }

    public static void runServerThread(ServerSocket serverSocket)
            throws IOException {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
                RunnableServer runnableServer =
                        new RunnableServer(socketWrapper);
                new Thread(runnableServer).start();
            }
        } catch (RuntimeException e) {
            throw new IOException(e);
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        runServerThread(buildServerSocket(portNumber));
    }
}
