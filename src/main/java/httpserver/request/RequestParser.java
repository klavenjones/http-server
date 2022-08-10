package httpserver.request;

import java.util.HashMap;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.SP;

public class RequestParser {

    private final String incomingRequest;
    private HashMap<String, String> headers = new HashMap<>();
    private String method;
    private String path;
    private String version;
    private String body;

    public RequestParser(String incomingData) {
        this.incomingRequest = incomingData;
    }

    public Request parse() {
        this.method = getRequestMethod();
        this.path = getRequestPath();
        this.version = getRequestVersion();
        this.headers = getRequestHeaders();
        this.body = getRequestBody();

        return new Request(version, method, path, headers, body);
    }


    public String getRequestMethod() {
        return incomingRequest.split(SP)[0];
    }

    public String getRequestPath() {
        return incomingRequest.split(SP)[1];
    }

    public String getRequestVersion() {
        return incomingRequest.split(SP)[2];
    }

    public HashMap<String, String> getRequestHeaders() {
        String[] headerArray = incomingRequest.split(CRLF);
        for (int i = 1; i < headerArray.length; i++) {
            String[] headerPair = headerArray[i].split(": ");
            if (headerPair.length == 2) {
                String key = headerPair[0];
                String value;
                if (i == headerArray.length - 1) {
                    value = headerPair[1].split(CRLF + CRLF, 2)[0];
                } else {
                    value = headerPair[1];
                }
                this.headers.put(key, value);
            }
        }
        return headers;
    }

    public String getRequestBody() {
        return incomingRequest.split(CRLF + CRLF, 2)[1];
    }


}
