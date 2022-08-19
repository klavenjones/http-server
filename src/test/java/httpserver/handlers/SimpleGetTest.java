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

class SimpleGetTest {

    @Test
    @DisplayName("Should return the response Status Line")
    public void testIfHandlerReturnsStatusLine() {
        SimpleGet simpleGetHandler = new SimpleGet();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("/simple_get"));
        Request request = requestParser.parse();
        Response response = simpleGetHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String simpleGetResponse = responseFormatter.formatResponse(response);


        assertEquals(simpleGetResponse,
                "HTTP/1.1 200 OK" + CRLF + "Allow: GET, HEAD, OPTIONS, POST" +
                        CRLF + "Content-Length: 0" + CRLF + CRLF);
    }

    @Test
    @DisplayName("Should return the response with body")
    public void testIfHandlerReturnsResponseWithBody() {
        SimpleGet simpleGetHandler = new SimpleGet();
        RequestParser requestParser = new RequestParser(
                TestUtils.mockGetData("/simple_get_with_body"));
        Request request = requestParser.parse();
        Response response = simpleGetHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String simpleGetResponse = responseFormatter.formatResponse(response);

        assertEquals(simpleGetResponse,
                "HTTP/1.1 200 OK" + CRLF + "Allow: GET, HEAD, OPTIONS, POST" +
                        CRLF + "Content-Length: 11" + CRLF + CRLF +
                        "Hello world");
    }


}

