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
    private String body = "";

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
        return incomingRequest.split(SP, 0)[1];
    }

    public String getRequestVersion() {
        return incomingRequest.split(SP, 0)[2];
    }

    public HashMap<String, String> getRequestHeaders() {
        //This will create a string array seperated by CRLF
        // where each element is one line of the request
        String[] requestString = incomingRequest.split(CRLF);
        return parseKeyValuePairs(requestString, headers);
    }

    public String getRequestBody() {
        return incomingRequest.split(CRLF + CRLF, 2)[1];
    }


    public HashMap<String, String> parseKeyValuePairs(
            String[] requestString, HashMap<String, String> headers) {
        //Iterate through the string array starting
        // from the second line of the string
        for (int i = 1; i < requestString.length; i++) {
            String[] headerPair = requestString[i].split(": ");
            if (headerPair.length == 2) {
                String key = headerPair[0];
                String value;
                if (i == requestString.length - 1) {
                    value = headerPair[1].split(CRLF + CRLF, 2)[0];
                } else {
                    value = headerPair[1];
                }
                headers.put(key, value);
            }
        }
        return headers;
    }

}

