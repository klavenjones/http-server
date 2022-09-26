package httpserver.handlers;

import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundTest {
    @Test
    @DisplayName("Test should return 404 Not Found")
    public void testIfHandlerReturnsNotFound() throws IOException {
        NotFound notFound = new NotFound();
        Response response = notFound.handle();
        ResponseFormatter responseFormatter = new ResponseFormatter();
        String notFoundResponse =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);

        assertEquals(notFoundResponse, "HTTP/1.1 404 Not Found" + CRLF + CRLF);

    }
}
