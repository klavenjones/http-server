package httpserver.handlers;

import httpserver.interfaces.IHandler;

import static httpserver.constants.HTTPLines.CRLF;

public class Options implements IHandler {

    private final String allowed;
    private String requestMethod;

    private final StringBuilder response = new StringBuilder();

    public Options(String methods) {
        this.allowed = methods;
    }

    @Override
    public String handle() {
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append("Allow: " + allowed + CRLF);
        response.append(CRLF);

        return response.toString();
    }

    public String handle(String methodFromClient) {
        this.requestMethod = methodFromClient;
        if (!allowed.contains(requestMethod)) {
            response.append("HTTP/1.1 405 Method Not Allowed" + CRLF);
        } else {
            response.append("HTTP/1.1 200 OK" + CRLF);
        }
        response.append("Allow: " + allowed + CRLF);
        response.append(CRLF);
        return response.toString();
    }

}

