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

class OptionsTest {
    @Test
    @DisplayName("Should test for proper Status line")
    public void testIfHandlerReturnsCorrectStatusLine() throws IOException {
        IHandler optionsHandler = new Options();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();

        Response response = optionsHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String statusLine =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8).split(CRLF)[0];
        assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("Should test for proper Allow Header")
    public void testIfHandlerReturnsCorrectAllowHeader() throws IOException {
        IHandler optionsHandler = new Options();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();
        Response response = optionsHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String allowHeader =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8).split(CRLF)[1];
        assertEquals(allowHeader, "Allow: GET, HEAD, OPTIONS");
    }

    @Test
    @DisplayName("Should test for proper Allow Header given Five Headers")
    public void testIfHandlerReturnsAllowHeaderGivenFiveMethods()
            throws IOException {
        IHandler optionsHandler = new OptionsTwo();
        RequestParser requestParser = new RequestParser(dummyData());
        Request request = requestParser.parse();

        Response response = optionsHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();
        String allowHeader =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8).split(CRLF)[1];

        assertEquals(allowHeader, "Allow: GET, HEAD, OPTIONS, PUT, POST");
    }


    public String dummyData() {
        return "GET /simple_get_with_body HTTP/1.1" + CRLF +
                "Connection: close" + CRLF + "Host: 127.0.0.1:5000" + CRLF +
                "User-Agent: http.rb/4.3.0" + CRLF + "Content-Length: 0" +
                CRLF + CRLF + "";
    }


}

