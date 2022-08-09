package httpserver.handlers;

import httpserver.interfaces.IHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotAllowedTest {
    @Test
    @DisplayName("Should return 405 Method Not Allowed")
    public void testIfHandlerSends405StatusLine() {
        IHandler optionsHandler = new NotAllowed();
        String statusLine = optionsHandler.handle("GET").split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 405 Method Not Allowed");
    }

}
