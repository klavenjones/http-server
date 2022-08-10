package httpserver.handlers;

import httpserver.request.Request;
import httpserver.request.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleGetTest {

    @Test
    @DisplayName("Should return the response Status Line")
    public void testIfHandlerReturnsStatusLine() {
        SimpleGet simpleGetHandler = new SimpleGet();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        assertEquals(simpleGetHandler.handle(request),
                "HTTP/1.1 200 OK" + CRLF + CRLF);
    }

    @Test
    @DisplayName("Should return the response with body")
    public void testIfHandlerReturnsResponseWithBody() {
        SimpleGet simpleGetHandler = new SimpleGet("Hello world");
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        assertEquals(simpleGetHandler.handle(request),
                "HTTP/1.1 200 OK" + CRLF + CRLF + "Hello world");
    }

    public String dummyData() {
        return "GET /simple_get HTTP/1.1\n" +
                "Connection: close\n" +
                "Host: 127.0.0.1:5000\n" +
                "User-Agent: http.rb/4.3.0\n" +
                "Content-Length: 0" + CRLF + CRLF;
    }


}

