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
        IHandler optionsHandler = new Options("OPTIONS, GET, HEAD");
        String statusLine = optionsHandler.handle().split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("Should test for proper Allow Header")
    public void testIfHandlerReturnsCorrectAllowHeader() {
        IHandler optionsHandler = new Options("OPTIONS, GET, HEAD");
        String allowHeader = optionsHandler.handle().split(CRLF)[1];
        assertEquals(allowHeader, "Allow: OPTIONS, GET, HEAD");
    }

    @Test
    @DisplayName("Should test for proper Allow Header given Five Headers")
    public void testIfHandlerReturnsAllowHeaderGivenFiveMethods() {
        IHandler optionHandler = new Options("OPTIONS, GET, HEAD, PUT, POST");
        String allowHeader = optionHandler.handle().split(CRLF)[1];
        assertEquals(allowHeader, "Allow: OPTIONS, GET, HEAD, PUT, POST");
    }
}
