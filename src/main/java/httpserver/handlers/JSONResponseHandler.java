package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.response.Response;
import httpserver.response.ResponseBuilder;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;

public class JSONResponseHandler implements IHandler {

    private final ResponseBuilder responseBuilder = new ResponseBuilder();

    @Override
    public Response handle(Request request) {
        String body = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        //Possible Abstraction and Extraction
        if (isMethodAllowed(request.method)) {
            return responseBuilder.withStatus(OK.code)
                    .withHeader("Allow: " + getAcceptedMethods())
                    .withHeader("Content-Type: application/json;charset=utf-8")
                    .withHeader("Content-Length: " + body.length())
                    .withBody(body.getBytes()).build();
        } else {
            return responseBuilder.withStatus(METHOD_NOT_ALLOWED.code)
                    .withHeader("Allow: " + getAcceptedMethods()).build();
        }
    }

    //Utility methods
    @Override
    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(method)) {
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
        GET, HEAD, OPTIONS
    }
}
