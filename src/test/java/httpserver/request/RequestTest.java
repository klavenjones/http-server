package httpserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    Request requestParser;

    public void initialize(){
        requestParser = new Request(dummyRequestData());
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


    private static String dummyRequestData(){
        return "POST /echo_body HTTP/1.1 \n" +
                "Connection: close\n" +
                "Host: 127.0.0.1:5000\n" +
                "User-Agent: http.rb/4.3.0\n" +
                "Content-Length: 9\n" +
                "\r\n" +
                "some body";
    };

}