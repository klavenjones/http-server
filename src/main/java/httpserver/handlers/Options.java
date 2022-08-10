package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.HTTPLines.CRLF;

public class Options implements IHandler {

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
            response.append("HTTP/1.1 200 OK" + CRLF);
        } else {
            response.append("HTTP/1.1 405 Method Not Allowed" + CRLF);
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
        GET, HEAD, OPTIONS
    }

}

