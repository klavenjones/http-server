package httpserver.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


class HttpServerTest {

    @Test
    @DisplayName("Test if buildServerSocket returns ServerSocket")
    public void testIfReturningServerSocket() {
        ServerSocket serverSocket = HttpServer.buildServerSocket(3000);
        assertTrue(serverSocket instanceof ServerSocket);
    }

    @Test
    @DisplayName("Test if runServerThread throws the proper error")
    public void testIfErrorIsThrown() {
        HttpServer httpServer = new HttpServer();
        ServerSocket serverSocket = mock(ServerSocket.class);
        assertThrows(IOException.class,
                () -> httpServer.startServerThread(serverSocket));
    }


}
