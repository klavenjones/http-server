package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotAllowedTest {
    @Test
    @DisplayName("Should return 405 Method Not Allowed")
    public void testIfHandlerSends405StatusLine() {
        IHandler optionsHandler = new NotAllowed();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        String statusLine = optionsHandler.handle(request).split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 405 Method Not Allowed");
    }

    public String dummyData() {
        return "GET /head_request HTTP/1.1" + CRLF +
                "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF +
                "User-Agent: http.rb/4.3.0" + CRLF +
                "Content-Length: 0" + CRLF + CRLF;
    }
}



