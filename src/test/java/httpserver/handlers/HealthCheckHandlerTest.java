package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthCheckHandlerTest {
    String healthCheckResponse =
            "Allow: GET" + CRLF + "Content-Type: text/html;charset=utf-8" +
                    CRLF + "Content-Length: 166" + CRLF;

    @Test
    @DisplayName("Test if handler Returns the correct response")
    void testIfReturnCorrectResponse() {
        HealthCheckHandler healthCheckHandler = new HealthCheckHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("health-check.html"));

        Request request = requestParser.parse();
        Response response = healthCheckHandler.handle(request);

        assertEquals(response.status, "200 OK");
        assertEquals(response.headers, healthCheckResponse);
    }
}
