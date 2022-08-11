package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;
import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;

public class EchoHandler implements IHandler {

    String requestBody;

    public EchoHandler(String body) {
        this.requestBody = body;
    }

    @Override
    public String handle(Request request) {
        StringBuilder response = new StringBuilder();
        if (isMethodAllowed(request.method)) {
            response.append(DEFAULT_VERSION + SP + OK.code + CRLF);
        } else {
            response.append(DEFAULT_VERSION + SP
                    + METHOD_NOT_ALLOWED.code + CRLF);
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
