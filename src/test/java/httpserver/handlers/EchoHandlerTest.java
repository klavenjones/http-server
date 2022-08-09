package httpserver.handlers;

import httpserver.interfaces.IHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoHandlerTest {
    @Test
    @DisplayName("Test is the response echos the body")
    public void testIfHandlerEchosBody() {
        IHandler echoHandler = new EchoHandler("some body");
        String response = echoHandler.handle("GET").split(CRLF + CRLF, 2)[1];
        assertEquals(response, "some body");
    }
}
