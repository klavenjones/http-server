package httpserver.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpServerTest {

    @Test
    @DisplayName("Test if it's returning a ServerSocket")
    public void testIfReturningServerSocket() {
        ServerSocket serverSocket = HttpServer.buildServerSocket(3000);
        assertTrue(serverSocket instanceof ServerSocket);
    }

}
