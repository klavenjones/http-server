package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONResponseTest {
    String textResponse = "HTTP/1.1 200 OK" + CRLF
            + "Content-Type: application/json;charset=utf-8" + CRLF
            + "Content-Length: 33" + CRLF + CRLF +
            "{\"key1\":\"value1\",\"key2\":\"value2\"}";

    @Test
    @DisplayName("Should return the correct response with body")
    public void testIfHandlerReturnsCorrectResponse() {
        JSONResponse jsonResponseHandler =
                new JSONResponse("{\"key1\":\"value1\",\"key2\":\"value2\"}");
        RequestParser requestParser =
                new RequestParser(TestUtils.dummyGetData("json_response"));
        Request request = requestParser.parse();
        assertEquals(jsonResponseHandler.handle(request),
                textResponse);
    }
}