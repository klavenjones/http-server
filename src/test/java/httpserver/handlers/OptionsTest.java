package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsTest {
    @Test
    @DisplayName("Should test for proper Status line")
    public void testIfHandlerReturnsCorrectStatusLine() {
        IHandler optionsHandler = new Options();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        String statusLine = optionsHandler.handle(request).split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("Should test for proper Allow Header")
    public void testIfHandlerReturnsCorrectAllowHeader() {
        IHandler optionsHandler = new Options();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        String allowHeader = optionsHandler.handle(request).split(CRLF)[1];
        assertEquals(allowHeader, "Allow: GET, HEAD, OPTIONS");
    }

    @Test
    @DisplayName("Should test for proper Allow Header given Five Headers")
    public void testIfHandlerReturnsAllowHeaderGivenFiveMethods() {
        IHandler optionHandler = new OptionsTwo();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        String allowHeader = optionHandler.handle(request).split(CRLF)[1];
        assertEquals(allowHeader, "Allow: GET, HEAD, OPTIONS, PUT, POST");
    }


    public String dummyData() {
        return "GET /simple_get_with_body HTTP/1.1" + CRLF +
                "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF +
                "User-Agent: http.rb/4.3.0" + CRLF +
                "Content-Length: 0" + CRLF + CRLF + "";
    }


}

