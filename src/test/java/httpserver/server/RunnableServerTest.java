package httpserver.server;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RunnableServerTest {

    private static SocketMock buildMockSocket(InputStream in, OutputStream out,
                                              String requestData) throws
            IOException {
        SocketMock clientSocket = mock(SocketMock.class);
        when(clientSocket.receiveData()).thenReturn(requestData);
        when(clientSocket.isConnectionClosed()).thenReturn(true);
        return clientSocket;
    }

    private static String dummyRequestData() {
        return "POST /echo_body HTTP/1.1 \n" +
                "Connection: close\n" +
                "Host: 127.0.0.1:5000\n" +
                "User-Agent: http.rb/4.3.0\n" +
                "Content-Length: 9\n" +
                "\n\r" +
                "some body";
    }

    @Test
    @DisplayName("Test to if the server sent the data")
    public void testIfSocketSentData() throws IOException {

        ByteArrayInputStream reader =
                new ByteArrayInputStream(dummyRequestData().getBytes());
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        SocketMock socket = buildMockSocket(reader, writer, dummyRequestData());

        assertEquals(socket.receiveData(), dummyRequestData());
    }

    @Test
    @DisplayName("Test to if the server connection is closed")
    public void testIfConnectionIsClosed() throws IOException {
        ByteArrayInputStream reader =
                new ByteArrayInputStream(dummyRequestData().getBytes());
        ByteArrayOutputStream writer = new ByteArrayOutputStream();

        SocketMock socket = buildMockSocket(reader, writer, dummyRequestData());

        RunnableServer runnableServer = new RunnableServer(socket);
        runnableServer.run();

        assertTrue(socket.isConnectionClosed());
    }


}




