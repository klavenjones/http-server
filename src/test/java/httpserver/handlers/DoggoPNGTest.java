package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DoggoPNGTest {

    @Test
    @DisplayName("Test if handler returns the correct response")
    void testIfHandlerReturnsCorrectResponse() {
        String expectedResponse =
                "HTTP/1.1 200 OK" + CRLF + "Allow: GET" + CRLF +
                        "Content-Type: image/png;" + CRLF +
                        "Content-Length: 68" + CRLF + CRLF +
                        "/Users/8thlight/Documents/http-server/" +
                        "http-server-spec/web/doggo.png";

        DoggoPNG doggoPNG = new DoggoPNG();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("doggo.png"));
        Request request = requestParser.parse();
        Response response = doggoPNG.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String pngResponse = responseFormatter.formatResponse(response);

        assertEquals(pngResponse, expectedResponse);
    }
}
