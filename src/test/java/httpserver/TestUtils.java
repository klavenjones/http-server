package httpserver;

import httpserver.request.Request;

import java.util.HashMap;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;
import static httpserver.constants.StatusCode.OK;

public class TestUtils {

    public static String mockRequestData() {
        return "POST /echo_body HTTP/1.1 " + CRLF + "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF + "User-Agent: http.rb/4.3.0" +
                CRLF + "Content-Length: 9" + CRLF + CRLF + "some body";
    }

    public static String mockEchoData() {
        return "GET /simple_get HTTP/1.1" + CRLF + "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF + "User-Agent: http.rb/4.3.0" +
                CRLF + "Content-Length: 9" + CRLF + CRLF + "some body";
    }

    public static String mock404Data() {
        return "GET /simple_non_existent HTTP/1.1" + CRLF +
                "Connection: close" + CRLF + "Host: 127.0.0.1:5000" + CRLF +
                "User-Agent: http.rb/4.3.0" + CRLF + "Content-Length: 9" +
                CRLF + CRLF + "some body";
    }

    public static String mockGetData(String pathName) {
        return "GET" + SP + pathName + SP + "HTTP/1.1\n" +
                "Connection: close\n" + "Host: 127.0.0.1:5000\n" +
                "User-Agent: http.rb/4.3.0\n" + "Content-Length: 0" + CRLF +
                CRLF;
    }

    public static String mockResponse() {
        return DEFAULT_VERSION + SP + OK.code + CRLF +
                "Allow: GET, HEAD, OPTIONS, PUT, POST" + CRLF +
                "Content-Length: 9" + CRLF + CRLF + "some body";
    }

    public static String mockResponseWithContentLength(String length) {
        return DEFAULT_VERSION + SP + OK.code + CRLF + "Content-Length:" + SP +
                length + CRLF + CRLF + "";
    }

    public static String mockResponseWithMultipleHeaders(String length,
                                                         String body) {
        return DEFAULT_VERSION + SP + OK.code + CRLF + "Content-Length:" + SP +
                length + CRLF + "Allow:" + SP + "GET, HEAD, OPTIONS" + CRLF +
                CRLF + body;
    }


    public static Request buildRequest(String version, String method,
                                       String path, String body,
                                       HashMap<String, String> headers) {
        return new Request(version, method, path, headers, body);
    }

}


