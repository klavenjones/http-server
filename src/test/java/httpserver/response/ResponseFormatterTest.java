package httpserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;
import static httpserver.constants.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseFormatterTest {

    @Test
    @DisplayName("Test if the response is formats correct startLine")
    public void testIfResponseFormattedCorrectStartLine() throws IOException {
        String body = "";
        String headers = "";
        Response response = new Response(OK.code, headers, body.getBytes());
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String expectedResponse = DEFAULT_VERSION + SP + OK.code + CRLF + CRLF;
        String formattedResponse =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);
        assertEquals(formattedResponse, expectedResponse);
    }
}

