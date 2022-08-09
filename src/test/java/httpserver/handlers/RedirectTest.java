package httpserver.handlers;

import httpserver.interfaces.IHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RedirectTest {
    @Test
    @DisplayName("Should return the appropriate Status Line response")
    public void testIfHandlerReturnsCorrectStatusLine() {
        IHandler redirectHandler = new Redirect();
        String statusLine = redirectHandler.handle("GET").split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 301 Moved Permanently");
    }

    @Test
    @DisplayName("Should return the Location Header with the correct url")
    public void testIfHandlerReturnsTheCorrectHeader() {
        IHandler redirectHandler = new Redirect();
        String locationLine = redirectHandler.handle("GET").split(CRLF)[2];
        assertEquals(locationLine,
                "Location: http://127.0.0.1:5000/simple_get");
    }
}
