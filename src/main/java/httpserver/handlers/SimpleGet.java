package httpserver.handlers;

import httpserver.interfaces.IHandler;

import static httpserver.constants.HTTPLines.CRLF;

public class SimpleGet implements IHandler {

    private final String body;
    private final StringBuilder response = new StringBuilder();

    public SimpleGet(String responseBody) {
        this.body = responseBody;
    }

    public SimpleGet() {
        this.body = "";
    }

    @Override
    public String handle(String methodFromClient) {
        if (isMethodAllowed(methodFromClient)) {
            response.append("HTTP/1.1 200 OK" + CRLF);
        } else {
            response.append("HTTP/1.1 405 Method Not Allowed" + CRLF);
        }
        response.append(CRLF);
        response.append(body);

        return response.toString();
    }

    @Override
    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(method)) {
                return true;
            }
        }
        return false;
    }

    public enum AcceptedMethods {
        GET, HEAD, OPTIONS, POST
    }


}
