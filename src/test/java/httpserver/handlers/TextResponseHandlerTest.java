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

class TextResponseHandlerTest {

    String textResponseToClient = "HTTP/1.1 200 OK" + CRLF
            + "Allow: GET, HEAD, OPTIONS" + CRLF
            + "Content-Type: text/plain;charset=utf-8" + CRLF
            + "Content-Length: 13" + CRLF + CRLF + "text response";

    @Test
    @DisplayName("Should return the correct response with body")
    public void testIfHandlerReturnsCorrectResponse() throws IOException {
        TextResponseHandler textResponseHandler = new TextResponseHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("text_response"));
        Request request = requestParser.parse();
        Response response = textResponseHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String textResponse =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);

        assertEquals(textResponse, textResponseToClient);
    }


}

