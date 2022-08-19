package httpserver.router;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    @Test
    @DisplayName("Test if response 200 OK is sent")
    public void testIfTheResponseIs200OK() {
        RequestParser requestParser =
                new RequestParser(TestUtils.mockRequestData());
        Request request = requestParser.parse();
        Router router = new Router();

        Response response = router.handleRequest(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();
        String formattedResponse =
                responseFormatter.formatResponse(response).split(CRLF)[0];

        assertEquals(formattedResponse, "HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("Test if response 404 was sent")
    public void testIfTheNotFoundResponseSent() {
        RequestParser requestParser =
                new RequestParser(TestUtils.mock404Data());
        Request request = requestParser.parse();
        Router router = new Router();

        Response response = router.handleRequest(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();
        String formattedResponse =
                responseFormatter.formatResponse(response).split(CRLF)[0];

        assertEquals(formattedResponse, "HTTP/1.1 404 Not Found");
    }
}
