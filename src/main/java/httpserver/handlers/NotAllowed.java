package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.HTTPLines.*;
import static httpserver.constants.StatusCode.*;

public class NotAllowed implements IHandler {

    private final StringBuilder response = new StringBuilder();

    @Override
    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(method)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            response.append(DEFAULT_VERSION + SP + OK.code + CRLF);
        } else {
            response.append(DEFAULT_VERSION + SP + METHOD_NOT_ALLOWED.code + CRLF);
        }
        response.append("Allow: " + getMethods() + CRLF);
        response.append(CRLF);
        return response.toString();
    }

    private String getMethods() {
        List<String> methods = new LinkedList<>();
        for (AcceptedMethods acceptedMethod : AcceptedMethods.values()) {
            methods.add(acceptedMethod.name());
        }
        return String.join(", ", methods);
    }

    public enum AcceptedMethods {
        HEAD, OPTIONS
    }

}

