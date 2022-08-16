package httpserver.response;

import httpserver.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseBuilderTest {
    private final ResponseBuilder responseBuilder = new ResponseBuilder();

    @Test
    @DisplayName("It should return a StartLine with a Status off 200")
    public void testIfBuilderReturnResponseWithOkStatus() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String response =
                responseBuilder.withStatus(OK.code).build().split(CRLF, 2)[0];
        assertEquals(response, "HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("It should return a response with one header")
    public void testIfBuilderReturnResponseWithHeaders() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String response =
                responseBuilder.withStatus(OK.code)
                        .withHeader("Content-Length: 0").build();
        assertEquals(response, TestUtils.dummyResponseWithContentLength("0"));
    }

    @Test
    @DisplayName("It should return a response with multiple headers")
    public void testIfBuilderReturnResponseWithMultipleHeaders() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String response = responseBuilder.withStatus(OK.code)
                .withHeader("Content-Length: 0")
                .withHeader("Allow: GET, HEAD, OPTIONS").build();
        System.out.println(response);
        assertEquals(response,
                TestUtils.dummyResponseWithMultipleHeaders("0", ""));
    }

    @Test
    @DisplayName("It should return a response with multiple headers")
    public void testIfBuilderReturnResponseWithBody() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String body = "some body";
        String response =
                responseBuilder.withStatus(OK.code)
                        .withHeader("Content-Length: " + body.length())
                        .withHeader("Allow: GET, HEAD, OPTIONS")
                        .withBody(body).build();
        System.out.println(response);
        assertEquals(response, TestUtils.dummyResponseWithMultipleHeaders(
                String.valueOf(body.length()), body));
    }


}
