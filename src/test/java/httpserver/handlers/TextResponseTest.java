package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.*;

class TextResponseTest {

    String textResponse = "HTTP/1.1 200 OK" + CRLF
            + "Content-Type: text/plain;charset=utf-8" + CRLF
            +  "Content-Length: 9" + CRLF + CRLF + "Some Text";

    @Test
    @DisplayName("Should return the correct response with body")
    public void testIfHandlerReturnsCorrectResponse() {
        TextResponse textResponseHandler = new TextResponse("Some Text");
        RequestParser requestParser =
                new RequestParser(TestUtils.dummyGetData("text_response"));
        Request request = requestParser.parse();
        assertEquals(textResponseHandler.handle(request),
                textResponse);
    }


}
