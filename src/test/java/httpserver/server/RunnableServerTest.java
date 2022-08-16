package httpserver.server;


import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.router.Router;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;
import static httpserver.constants.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RunnableServerTest {

    private static String getDummyResponse() {
        return DEFAULT_VERSION + SP + OK.code + CRLF
                + "Allow: GET, HEAD, OPTIONS, PUT, POST" + CRLF + CRLF
                + "some body";
    }

    private static SocketMock buildMockSocket(String requestData) {
        SocketMock clientSocket = mock(SocketMock.class);
        when(clientSocket.receiveData()).thenReturn(requestData);
        when(clientSocket.isConnectionClosed()).thenReturn(true);

        return clientSocket;
    }

    @Test
    @DisplayName("Test to if the server received the data")
    public void testIfSocketSentData() throws IOException {
        SocketMock socket = buildMockSocket(TestUtils.dummyRequestData());
        assertEquals(socket.receiveData(), TestUtils.dummyRequestData());
    }

    @Test
    @DisplayName("Test to if the server connection is closed")
    public void testIfConnectionIsClosed() throws IOException {
        SocketMock socket = buildMockSocket(TestUtils.dummyRequestData());
        RunnableServer runnableServer = new RunnableServer(socket);
        runnableServer.run();
        assertTrue(socket.isConnectionClosed());
    }

    @Test
    @DisplayName("Test if correct data was sent")
    public void testIfCorrectDataSent() {

        Router router = new Router();
        RequestParser requestParser =
                new RequestParser(TestUtils.dummyRequestData());

        Request request = requestParser.parse();
        SocketMock socket = new SocketMock();
        socket.sendData(router.handleRequest(request));

        assertEquals(socket.dataSent, getDummyResponse());
    }

}




