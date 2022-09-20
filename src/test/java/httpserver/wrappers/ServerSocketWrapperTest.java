package httpserver.wrappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ServerSocketWrapperTest {

    @Test
    @DisplayName("Test if create server Socket returns a serverSocket")
    void testIfReturnsServerSocket() {
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper();
        ServerSocket serverSocket =
                serverSocketWrapper.createServerSocket(5000);
        assertTrue(serverSocket instanceof ServerSocket);
    }

    @Test
    @DisplayName("Test if correct error is thrown in ServerSocketWrapper.createServerSocket")
    void testIfCorrectErrorIsThrownInCreateServerSocket() {
        ServerSocketWrapper serverSocket = mock(ServerSocketWrapper.class);

        doThrow(new RuntimeException()).when(serverSocket)
                .createServerSocket(5000);

        assertThrows(RuntimeException.class,
                () -> serverSocket.createServerSocket(5000));
    }


    @Test
    @DisplayName("Test if correct error is thrown in ServerSocketWrapper.close()")
    void testIfCorrectErrorIsThrownInCloseMethod() {
        ServerSocketWrapper serverSocket = mock(ServerSocketWrapper.class);

        doThrow(new RuntimeException()).when(serverSocket).close();

        assertThrows(RuntimeException.class, () -> serverSocket.close());
    }
}