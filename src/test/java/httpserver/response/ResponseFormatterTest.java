package httpserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;
import static httpserver.constants.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseFormatterTest {

    @Test
    @DisplayName("Test if the response is formats correct startLine")
    public void testIfResponseFormattedCorrectStartLine() {
        String body = "";
        String headers = "";
        Response response = new StringResponse(OK.code, headers, body);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String expectedResponse = DEFAULT_VERSION + SP + OK.code + CRLF + CRLF;

        String formattedResponse = responseFormatter.formatResponse(response);
        assertEquals(formattedResponse, expectedResponse);
    }
}
