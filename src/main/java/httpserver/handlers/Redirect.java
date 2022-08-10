package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.HTTPLines.CRLF;

public class Redirect implements IHandler {

    public enum AcceptedMethods {
        GET, HEAD, OPTIONS, PUT, POST
    }

    @Override
    public String handle(Request request) {
        StringBuilder response = new StringBuilder();
        if (isMethodAllowed(request.method)) {
            response.append("HTTP/1.1 301 Moved Permanently" + CRLF);
        } else {
            response.append("HTTP/1.1 405 Method Not Allowed" + CRLF);
        }
        response.append("Allow: " + getMethods() + CRLF);
        response.append("Location: http://127.0.0.1:5000/simple_get" + CRLF);
        response.append(CRLF);

        return response.toString();
    }

    @Override
    public boolean isMethodAllowed(String requestMethod) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(requestMethod)) {
                return true;
            }
        }
        return false;
    }

    private String getMethods() {
        List<String> methods = new LinkedList<>();
        for (AcceptedMethods acceptedMethod : AcceptedMethods.values()) {
            methods.add(acceptedMethod.name());
        }
        return String.join(", ", methods);
    }





}
