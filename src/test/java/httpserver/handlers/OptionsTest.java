package httpserver.handlers;

import httpserver.interfaces.IHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsTest {
    @Test
    @DisplayName("Should test for proper Status line")
    public void testIfHandlerReturnsCorrectStatusLine() {
        IHandler optionsHandler = new Options();
        String statusLine = optionsHandler.handle("OPTIONS").split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("Should test for proper Allow Header")
    public void testIfHandlerReturnsCorrectAllowHeader() {
        IHandler optionsHandler = new Options();
        String allowHeader = optionsHandler.handle("OPTIONS").split(CRLF)[1];
        assertEquals(allowHeader, "Allow: GET, HEAD, OPTIONS");
    }

    @Test
    @DisplayName("Should test for proper Allow Header given Five Headers")
    public void testIfHandlerReturnsAllowHeaderGivenFiveMethods() {
        IHandler optionHandler = new OptionsTwo();
        String allowHeader = optionHandler.handle("OPTIONS").split(CRLF)[1];
        assertEquals(allowHeader, "Allow: GET, HEAD, OPTIONS, PUT, POST");
    }
}
