package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RedirectTest {
    @Test
    @DisplayName("Should return the appropriate Status Line response")
    public void testIfHandlerReturnsCorrectStatusLine() throws IOException {
        IHandler redirectHandler = new Redirect();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        Response response = redirectHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String statusLine =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8).split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 301 Moved Permanently");
    }

    @Test
    @DisplayName("Should return the Location Header with the correct url")
    public void testIfHandlerReturnsTheCorrectHeader() throws IOException {
        IHandler redirectHandler = new Redirect();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        String host = request.headers.get("Host");
        Response response = redirectHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        System.out.println(host);


        String locationLine =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8).split(CRLF)[2];
        assertEquals(locationLine,
                "Location: http://" + host + "/simple_get");
    }

    public String dummyData() {
        return "GET /redirect HTTP/1.1" + CRLF + "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF + "User-Agent: http.rb/4.3.0" +
                CRLF + "Content-Length: 0" + CRLF + CRLF + "";
    }
}
