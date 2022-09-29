package httpserver.server;


import httpserver.interfaces.IServerSocket;
import httpserver.wrappers.ServerSocketWrapper;
import httpserver.wrappers.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private static IServerSocket serverSocketWrapper;
    private static ExecutorService pool;

    public static ServerSocket buildServerSocket(int portNumber) {
        serverSocketWrapper = new ServerSocketWrapper();
        int processingCoresAvailable = Runtime.getRuntime()
                .availableProcessors();
        pool = Executors.newFixedThreadPool(processingCoresAvailable);
        return serverSocketWrapper.createServerSocket(portNumber);
    }

    public static void startServerThread(ServerSocket serverSocket)
            throws IOException {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
                RunnableServer runnableServer =
                        new RunnableServer(socketWrapper);
                Thread thread = new Thread(runnableServer);
                pool.execute(thread);
            }
        } catch (RuntimeException e) {
            throw new IOException(e);
        } finally {
            System.out.println("Socket Closed");
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);
        startServerThread(buildServerSocket(portNumber));
    }
}
