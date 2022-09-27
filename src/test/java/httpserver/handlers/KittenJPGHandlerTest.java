package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KittenJPGHandlerTest {
    String kittenResponseHeaders =
            "Allow: GET" + CRLF + "Content-Type: image/jpeg" + CRLF +
                    "Content-Length: 207922" + CRLF;

    @Test
    @DisplayName("Test if handler Returns the correct response")
    void testIfReturnCorrectResponse() {
        KittenJPGHandler kittenJPGHandler = new KittenJPGHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("kitteh.png"));

        Request request = requestParser.parse();
        Response response = kittenJPGHandler.handle(request);

        assertEquals(response.status, "200 OK");
        assertEquals(response.headers, kittenResponseHeaders);
    }
}
