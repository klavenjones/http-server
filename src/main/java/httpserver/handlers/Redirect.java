package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.response.Response;
import httpserver.response.ResponseBuilder;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.MOVED_PERMANENTLY;


public class Redirect implements IHandler {

    private final ResponseBuilder responseBuilder = new ResponseBuilder();

    @Override
    public Response handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return responseBuilder.withStatus(MOVED_PERMANENTLY.code)
                    .withHeader("Allow: " + getAcceptedMethods())
                    .withHeader("Location: http://127.0.0.1:5000/simple_get")
                    .build();

        } else {
            return responseBuilder.withStatus(METHOD_NOT_ALLOWED.code)
                    .withHeader("Allow: " + getAcceptedMethods()).build();
        }
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

    public String getAcceptedMethods() {
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
