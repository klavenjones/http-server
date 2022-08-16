package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoHandlerTest {
    @Test
    @DisplayName("Test is the response echos the body")
    public void testIfHandlerEchosBody() {
        IHandler echoHandler = new EchoHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.dummyEchoData());
        Request request = requestParser.parse();
        String response = echoHandler.handle(request).split(CRLF + CRLF, 2)[1];
        assertEquals(response, "some body");
    }

}
