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

class HTMLResponseHandlerTest {
    String textResponse =
            "HTTP/1.1 200 OK" + CRLF + "Allow: GET, HEAD, OPTIONS" + CRLF +
                    "Content-Type: text/html;charset=utf-8" + CRLF +
                    "Content-Length: 46" + CRLF + CRLF +
                    "<html><body><p>HTML Response</p></body></html>";

    @Test
    @DisplayName("Should return the correct response with body")
    public void testIfHandlerReturnsCorrectResponse() throws IOException {
        HTMLResponseHandler htmlResponseHandler = new HTMLResponseHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("html_response"));
        Request request = requestParser.parse();
        Response response = htmlResponseHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String htmlResponse =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);


        assertEquals(htmlResponse, textResponse);
    }
}

