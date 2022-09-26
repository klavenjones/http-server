package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoHandlerTest {
    @Test
    @DisplayName("Test is the response echos the body")
    public void testIfHandlerEchosBody() throws IOException {
        IHandler echoHandler = new EchoHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockEchoData());
        Request request = requestParser.parse();
        Response response = echoHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String responseBody = responseFormatter.formatResponse(response)
                .split(CRLF + CRLF, 2)[1];


        assertEquals(responseBody, "some body");
    }

}
