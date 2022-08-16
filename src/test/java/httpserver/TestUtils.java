package httpserver;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.SP;

public class TestUtils {

    public static String dummyRequestData() {
        return  "POST /echo_body HTTP/1.1 \n" +
                "Connection: close\n" +
                "Host: 127.0.0.1:5000\n" +
                "User-Agent: http.rb/4.3.0\n" +
                "Content-Length: 9\r\n" +
                "\r\n" +
                "some body";
    }

    public static String dummyEchoData() {
        return "GET /simple_get HTTP/1.1" + CRLF +
                "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF +
                "User-Agent: http.rb/4.3.0" + CRLF +
                "Content-Length: 9" + CRLF + CRLF +
                "some body";
    }

    public static String dummy404Data() {
        return "GET /simple_non_existent HTTP/1.1" + CRLF +
                "Connection: close" + CRLF +
                "Host: 127.0.0.1:5000" + CRLF +
                "User-Agent: http.rb/4.3.0" + CRLF +
                "Content-Length: 9" + CRLF + CRLF +
                "some body";
    }

    public static String dummyGetData(String pathName) {
        return  "GET" + SP + pathName + SP + "HTTP/1.1\n" +
                "Connection: close\n" +
                "Host: 127.0.0.1:5000\n" +
                "User-Agent: http.rb/4.3.0\n" +
                "Content-Length: 0" + CRLF + CRLF;
    }

}

