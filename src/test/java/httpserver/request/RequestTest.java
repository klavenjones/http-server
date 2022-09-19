package httpserver.request;

import httpserver.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {


    HashMap<String, String> headers = new HashMap<>();
    String method = "GET";
    String version = "HTTP 1.1";
    String path = "/test_path";
    String body = "Test Body";



    @Test
    @DisplayName("Testing if the request data object will return the appropriate method value")
    public void testIfRequestObjectReturnCorrectMethodValue(){
        Request request = TestUtils.buildRequest(version, method, path, body, headers);
        assertEquals(request.method, "GET");
    }

    @Test
    @DisplayName("Testing if the request data object will return the appropriate version value")
    public void testIfRequestObjectReturnCorrectVersionValue(){
        Request request = TestUtils.buildRequest(version, method, path, body, headers);
        assertEquals(request.version, "HTTP 1.1");
    }

    @Test
    @DisplayName("Testing if the request data object will return the appropriate path value")
    public void testIfRequestObjectReturnCorrectPathValue(){
        Request request = TestUtils.buildRequest(version, method, path, body, headers);
        assertEquals(request.path, "/test_path");
    }

    @Test
    @DisplayName("Testing if the request data object will return the appropriate body value")
    public void testIfRequestObjectReturnCorrectBodyValue(){
        Request request = TestUtils.buildRequest(version, method, path, body, headers);
        assertEquals(request.body, "Test Body");
    }

    @Test
    @DisplayName("Testing if the request data object will return the appropriate headers value")
    public void testIfRequestObjectReturnCorrectHeadersValue(){
        headers.put("Content-Length", "9");
        Request request = TestUtils.buildRequest(version, method, path, body, headers);
        assertEquals(request.headers.get("Content-Length"), "9");
    }

    @Test
    @DisplayName("Testing if the request data object will return the appropriate headers key")
    public void testIfRequestObjectReturnCorrectHeadersKey(){
        headers.put("Content-Length", "9");
        Request request = TestUtils.buildRequest(version, method, path, body, headers);
        assertEquals(request.headers.containsKey("Content-Length"), true);
    }

}