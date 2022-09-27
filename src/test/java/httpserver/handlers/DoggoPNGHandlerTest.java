package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DoggoPNGHandlerTest {
    String doggoResponseHeaders = "Allow: GET" + CRLF +
            "Content-Type: image/png" + CRLF +
            "Content-Length: 351702" + CRLF;

    @Test
    @DisplayName("Test if handler Returns the correct response")
    void testIfReturnCorrectResponse() throws IOException {
        DoggoPNGHandler doggoPNGHandler = new DoggoPNGHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("doggo.png"));
        Request request = requestParser.parse();

        Response response = doggoPNGHandler.handle(request);

        assertEquals(response.status, "200 OK");
        assertEquals(response.headers, doggoResponseHeaders);
    }
}
