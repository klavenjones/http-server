package httpserver.handlers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleGetTest {

    @Test
    @DisplayName("Should return the response Status Line")
    public void testIfHandlerReturnsStatusLine() {
        SimpleGet simpleGetHandler = new SimpleGet();
        assertEquals(simpleGetHandler.handle(),
                "HTTP/1.1 200 OK" + CRLF + CRLF);
    }

    @Test
    @DisplayName("Should return the response with body")
    public void testIfHandlerReturnsResponseWithBody() {
        SimpleGet simpleGetHandler = new SimpleGet("Hello world");
        assertEquals(simpleGetHandler.handle(),
                "HTTP/1.1 200 OK" + CRLF + CRLF + "Hello world");
    }


}

