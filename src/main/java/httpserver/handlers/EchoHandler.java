package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.response.Response;
import httpserver.response.ResponseBuilder;
import httpserver.response.ResponseFormatter;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;

public class EchoHandler implements IHandler {

    ResponseBuilder responseBuilder = new ResponseBuilder();
    Response response;
    ResponseFormatter responseFormatter = new ResponseFormatter();

    @Override
    public Response handle(Request request) {
        if (isMethodAllowed(request.method)) {
           return responseBuilder.withStatus(OK.code)
                    .withHeader("Allow: " + getMethods())
                    .withHeader("Content-Length: " + request.body.length())
                    .withBody(request.body).build();
        } else {
            return responseBuilder.withStatus(METHOD_NOT_ALLOWED.code)
                    .withHeader("Allow: " + getMethods()).build();
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
