package httpserver.server;


import httpserver.interfaces.IServerSocket;
import httpserver.wrappers.ServerSocketWrapper;
import httpserver.wrappers.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private static IServerSocket serverSocketWrapper;
    private static ServerSocket serverSocket;

    public static ServerSocket buildServerSocket(int portNumber) {
        serverSocketWrapper = new ServerSocketWrapper();
        return serverSocketWrapper.createServerSocket(portNumber);
    }

    public static void main(String[] args) throws IOException {

        try {
            if (args.length != 1) {
                System.err.println("Usage: java EchoServer <port number>");
                System.exit(1);
            }

            int portNumber = Integer.parseInt(args[0]);

            //Create ServerSocket
            serverSocket = buildServerSocket(portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
                RunnableServer runnableServer =
                        new RunnableServer(socketWrapper);
                new Thread(runnableServer).start();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);

        } finally {
            serverSocket.close();
        }


    }
}
