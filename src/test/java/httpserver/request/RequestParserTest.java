package httpserver.request;


import httpserver.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestParserTest {
    RequestParser requestParser;


    public void initialize() {
        requestParser = new RequestParser(TestUtils.dummyRequestData());
    }

    @Test
    @DisplayName("Test to if the server received a Post request Method")
    public void testIfServerRetrievedPostRequest() {
        initialize();
        assertEquals(requestParser.getRequestMethod(), "POST");
    }

    @Test
    @DisplayName("Test to if the server received the correct Path")
    public void testIfServerRetrievedRequestPath() {
        initialize();
        assertEquals(requestParser.getRequestPath(), "/echo_body");
    }

    @Test
    @DisplayName("Test to if the server received the correct HTTP Version")
    public void testIfServerRetrievedCorrectHTTPVersion() {
        initialize();
        assertEquals(requestParser.getRequestVersion(), "HTTP/1.1");
    }

    @Test
    @DisplayName("Test to if the server received the correct body")
    public void testIfServerRetrievedCorrectRequestBody() {
        initialize();
        assertEquals(requestParser.getRequestBody(), "some body");
    }

}


