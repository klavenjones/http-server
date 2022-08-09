package httpserver.handlers;

import httpserver.interfaces.IHandler;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.HTTPLines.CRLF;

public class EchoHandler implements IHandler {

    String requestBody;

    public EchoHandler(String body) {
        this.requestBody = body;
    }

    @Override
    public String handle(String methodFromClient) {
        StringBuilder response = new StringBuilder();
        if (isMethodAllowed(methodFromClient)) {
            response.append("HTTP/1.1 200 OK" + CRLF);
        } else {
            response.append("HTTP/1.1 405 Method Not Allowed" + CRLF);
        }
        response.append("Allow: " + getMethods() + CRLF);
        response.append(CRLF);
        response.append(requestBody);

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

    public enum AcceptedMethods {
        GET, HEAD, OPTIONS, PUT, POST
    }

}
