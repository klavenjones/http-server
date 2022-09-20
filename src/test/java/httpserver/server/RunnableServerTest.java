package httpserver.server;


import httpserver.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RunnableServerTest {


    private static SocketMock buildMockSocket(String requestData) {
        SocketMock clientSocket = mock(SocketMock.class);

        when(clientSocket.receiveData()).thenReturn(requestData);
        when(clientSocket.isConnectionClosed()).thenReturn(true);


        return clientSocket;
    }


    @Test
    @DisplayName("Test to if the server received the data")
    public void testIfSocketSentData() {
        SocketMock socket = buildMockSocket(TestUtils.mockRequestData());
        assertEquals(socket.receiveData(), TestUtils.mockRequestData());
    }

    @Test
    @DisplayName("Test to if the server connection is closed")
    public void testIfConnectionIsClosed() {
        SocketMock socket = buildMockSocket(TestUtils.mockRequestData());
        RunnableServer runnableServer = new RunnableServer(socket);
        runnableServer.run();
        assertTrue(socket.isConnectionClosed());
    }

    @Test
    @DisplayName("Test to if RunnableServer.run runs successfully")
    public void testIfRunMethodRuns() {
        SocketMock socket = buildMockSocket(TestUtils.mockRequestData());
        RunnableServer runnableServer =
                mock(new RunnableServer(socket).getClass());

        doNothing().when(runnableServer).run();
        runnableServer.run();

        verify(runnableServer, times(1)).run();
    }

    @Test
    @DisplayName("Test to if RunnableServer.run throws an error")
    public void testIfFunctionThrowsCorrectError() {
        SocketMock socket = buildMockSocket(TestUtils.mockRequestData());
        RunnableServer runnableServer =
                mock(new RunnableServer(socket).getClass());

        doThrow(new RuntimeException()).when(runnableServer).run();

        assertThrows(RuntimeException.class, () -> runnableServer.run());
    }


    @Test
    @DisplayName("Test if send data was called when parseMessage is called")
    public void testIfSendDataWasCalled() throws IOException {
        SocketMock socketMock = mock(SocketMock.class);
        doNothing().when(socketMock).sendData(TestUtils.mockResponse());

        RunnableServer runnableServer = new RunnableServer(socketMock);
        runnableServer.parseClientMessage(TestUtils.mockRequestData(),
                socketMock);
    }

}




