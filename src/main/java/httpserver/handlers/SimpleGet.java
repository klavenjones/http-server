package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.response.ResponseBuilder;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.Paths.SIMPLE_GET_WITH_BODY;
import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;

public class SimpleGet implements IHandler {

    private final ResponseBuilder responseBuilder = new ResponseBuilder();

    @Override
    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            String body = "Hello world";
            responseBuilder.withStatus(OK.code)
                    .withHeader("Allow: " + getMethods());

            if (request.path.equals(SIMPLE_GET_WITH_BODY.path)) {
                responseBuilder.withHeader("Content-Length: " + body.length())
                        .withBody(body);
            } else {
                responseBuilder.withHeader("Content-Length: 0");
            }
            return responseBuilder.build();
        } else {
            return responseBuilder.withStatus(METHOD_NOT_ALLOWED.code)
                    .withHeader("Allow: " + getMethods()).build();
        }
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

    private String getMethods() {
        List<String> methods = new LinkedList<>();
        for (AcceptedMethods acceptedMethod : AcceptedMethods.values()) {
            methods.add(acceptedMethod.name());
        }
        return String.join(", ", methods);
    }


    public enum AcceptedMethods {
        GET, HEAD, OPTIONS, POST
    }


}
