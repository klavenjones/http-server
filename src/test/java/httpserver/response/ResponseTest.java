package httpserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseTest {

    @Test
    @DisplayName("Test if the status is 200 OK")
    public void testIfStatusOK() {
        Response response = new Response(OK.code, "", "");
        assertEquals(response.status, "200 OK");
    }

    @Test
    @DisplayName("Test if it returns the correct body")
    public void testIfBodyIsCorrect() {
        String body = "Some Body";
        Response response = new Response(OK.code, "", body);
        assertEquals(response.body, "Some Body");
    }

    @Test
    @DisplayName("Test if it returns the correct body")
    public void testIfHeadersAreCorrect() {
        String body = "Some Body";
        String headers =
                "Content-Type: 9" + CRLF + "Allow: GET, OPTIONS, HEAD" + CRLF;
        Response response = new Response(OK.code, headers, body);
        assertEquals(response.headers, headers);
    }
}
