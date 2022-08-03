package httpserver.server;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RunnableServerTest {

    @Test
    @DisplayName("Test to if the server sent the data")
    public void testIfSocketSentData() {
        PrintWriter writer = new PrintWriter(new StringWriter(), true);
        BufferedReader reader = new BufferedReader(new StringReader("Test"));
        SocketMock socket = new SocketMock(reader, writer);

        RunnableServer runnableServer = new RunnableServer(socket);
        runnableServer.run();

        assertEquals(socket.dataSent, "Test");
    }


    @Test
    @DisplayName("Test to if the server connection is closed")
    public void testIfSocketSentMessage() {
        PrintWriter writer = new PrintWriter(new StringWriter(), true);
        BufferedReader reader = new BufferedReader(new StringReader("Test"));
        SocketMock socket = new SocketMock(reader, writer);

        RunnableServer runnableServer = new RunnableServer(socket);
        runnableServer.run();

        assertTrue(socket.isConnectionClosed());
    }

    @Test
    @DisplayName("Test to if the server received the data")
    public void testIfServerReceivedData() {
        PrintWriter writer = new PrintWriter(new StringWriter(), true);
        BufferedReader reader = new BufferedReader(new StringReader("Test"));
        SocketMock socket = new SocketMock(reader, writer);

        RunnableServer runnableServer = new RunnableServer(socket);
        runnableServer.run();

        assertEquals(socket.receivedData, "Test");
    }
}
