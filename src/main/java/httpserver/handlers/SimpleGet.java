package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;

import static httpserver.constants.HTTPLines.*;
import static httpserver.constants.StatusCode.*;

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
    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            response.append(DEFAULT_VERSION + SP + OK.code + CRLF);
        } else {
            response.append(DEFAULT_VERSION + SP + METHOD_NOT_ALLOWED.code + CRLF);
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
