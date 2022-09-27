package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KissesGIFHandlerTest {
    String kissesResponseHeaders =
            "Allow: GET" + CRLF + "Content-Type: image/gif" + CRLF +
                    "Content-Length: 432985" + CRLF;

    @Test
    @DisplayName("Test if handler Returns the correct response")
    void testIfReturnCorrectResponse() {
        KissesGIFHandler kissesGIFHandler = new KissesGIFHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("kisses.gif"));

        Request request = requestParser.parse();
        Response response = kissesGIFHandler.handle(request);

        assertEquals(response.status, "200 OK");
        assertEquals(response.headers, kissesResponseHeaders);
    }
}
