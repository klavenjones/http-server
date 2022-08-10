package httpserver.handlers;

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
        IHandler echoHandler = new EchoHandler("some body");
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        String response = echoHandler.handle(request).split(CRLF + CRLF, 2)[1];
        assertEquals(response, "some body");
    }

    public String dummyData() {
        return "POST /echo_body HTTP/1.1" + CRLF +
                "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF +
                "User-Agent: http.rb/4.3.0" + CRLF +
                "Content-Length: 9" + CRLF + CRLF +
                "some body";
    }
}
