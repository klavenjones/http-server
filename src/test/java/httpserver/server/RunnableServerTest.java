package httpserver.server;


import httpserver.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RunnableServerTest {
    private static SocketMock buildMockSocket(String requestData) {
        SocketMock clientSocket = mock(SocketMock.class);
        when(clientSocket.receiveData()).thenReturn(requestData);
        when(clientSocket.isConnectionClosed()).thenReturn(true);
        return clientSocket;
    }



    @Test
    @DisplayName("Test to if the server sent the data")
    public void testIfSocketSentData() {
        SocketMock socket = buildMockSocket(TestUtils.dummyRequestData());
        assertEquals(socket.receiveData(), TestUtils.dummyRequestData());
    }

    @Test
    @DisplayName("Test to if the server connection is closed")
    public void testIfConnectionIsClosed() {
        SocketMock socket = buildMockSocket(TestUtils.dummyRequestData());
        RunnableServer runnableServer = new RunnableServer(socket);
        runnableServer.run();
        assertTrue(socket.isConnectionClosed());
    }


}




