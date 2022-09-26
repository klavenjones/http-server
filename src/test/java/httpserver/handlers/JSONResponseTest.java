package httpserver.handlers;

import httpserver.TestUtils;
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

class JSONResponseTest {
    String textResponse =
            "HTTP/1.1 200 OK" + CRLF + "Allow: GET, HEAD, OPTIONS" + CRLF +
                    "Content-Type: application/json;charset=utf-8" + CRLF +
                    "Content-Length: 33" + CRLF + CRLF +
                    "{\"key1\":\"value1\",\"key2\":\"value2\"}";

    @Test
    @DisplayName("Should return the correct response with body")
    public void testIfHandlerReturnsCorrectResponse() throws IOException {
        JSONResponse jsonResponseHandler = new JSONResponse();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("json_response"));
        Request request = requestParser.parse();

        Response response = jsonResponseHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String jsonResponse =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);

        assertEquals(jsonResponse, textResponse);
    }
}

