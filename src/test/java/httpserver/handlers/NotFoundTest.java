package httpserver.handlers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundTest {
    @Test
    @DisplayName("Test should return 404 Not Found")
    public void testIfHandlerReturnsNotFound() {
        NotFound notFound = new NotFound();
        assertEquals(notFound.handle(), "HTTP/1.1 404 Not Found" + CRLF + CRLF);

    }
}
