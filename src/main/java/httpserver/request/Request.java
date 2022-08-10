package httpserver.request;

import java.util.HashMap;

public class Request {
    public String method;
    public String version;
    public String path;
    public String body;
    public HashMap<String, String> headers;


    public Request(String version, String method, String path,
                   HashMap<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.body = body;
        this.headers = headers;
    }
}
