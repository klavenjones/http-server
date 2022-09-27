package httpserver.response;

import httpserver.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseBuilderTest {
    @Test
    @DisplayName("It should return a StartLine with a Status off 200")
    public void testIfBuilderReturnResponseWithOkStatus() throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = responseBuilder.withStatus(OK.code).build();
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String formattedResponse =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8).split(CRLF, 2)[0];

        assertEquals(formattedResponse, "HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("It should return a response with one header")
    public void testIfBuilderReturnResponseWithHeaders() throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = responseBuilder.withStatus(OK.code)
                .withHeader("Content-Length: 0").build();
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String formattedString =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);

        assertEquals(formattedString,
                TestUtils.mockResponseWithContentLength("0"));
    }

    @Test
    @DisplayName("It should return a response with multiple headers")
    public void testIfBuilderReturnResponseWithMultipleHeaders()
            throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = responseBuilder.withStatus(OK.code)
                .withHeader("Content-Length: 0")
                .withHeader("Allow: GET, HEAD, OPTIONS").build();

        ResponseFormatter responseFormatter = new ResponseFormatter();

        String formattedString =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);

        assertEquals(formattedString,
                TestUtils.mockResponseWithMultipleHeaders("0", ""));
    }

    @Test
    @DisplayName("It should return a response with multiple headers")
    public void testIfBuilderReturnResponseWithBody() throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String body = "some body";
        Response response = responseBuilder.withStatus(OK.code)
                .withHeader("Content-Length: " + body.length())
                .withHeader("Allow: GET, HEAD, OPTIONS")
                .withBody(body.getBytes()).build();

        ResponseFormatter responseFormatter = new ResponseFormatter();

        String formattedString =
                new String(responseFormatter.formatResponse(response),
                        StandardCharsets.UTF_8);

        assertEquals(formattedString, TestUtils.mockResponseWithMultipleHeaders(
                String.valueOf(body.length()), body));
    }


}
