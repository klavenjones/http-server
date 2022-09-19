package httpserver.request;


import httpserver.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestParserTest {
    RequestParser requestParser;
    HashMap<String, String> testHeaders;


    public void initialize() {
        requestParser = new RequestParser(TestUtils.mockRequestData());
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

    @Test
    @DisplayName("Test if the server received the correct headers")
    public void testIfServerRetrievedCorrectRequestHeaders() {
        initialize();
        testHeaders = requestParser.getRequestHeaders();
        assertEquals(testHeaders.containsKey("Content-Length"), true);
        assertEquals(testHeaders.containsKey("Host"), true);
        assertEquals(testHeaders.containsKey("Connection"), true);
    }

    @Test
    @DisplayName("Test if parseKeyValuePairs add keys and values to map")
    public void testIfParseKeyValuePairsAddsKeyValuesToMap() {
        String incomingData = TestUtils.mockEchoData();

        //Check if the map is empty
        requestParser = new RequestParser(incomingData);
        testHeaders = new HashMap<>();
        assertEquals(testHeaders.isEmpty(), true);

        //Check if values were added to the map
        requestParser.parseKeyValuePairs(incomingData.split(CRLF), testHeaders);
        assertEquals(testHeaders.isEmpty(), false);
        assertEquals(testHeaders.containsKey("Connection"), true);
    }
}



