package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.response.Response;
import httpserver.response.ResponseBuilder;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;


public class NotAllowed implements IHandler {

    private final ResponseBuilder responseBuilder = new ResponseBuilder();


    //Util method?
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
    public Response handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return responseBuilder.withStatus(OK.code)
                    .withHeader("Allow: " + getAcceptedMethods()).build();
        } else {
            return responseBuilder.withStatus(METHOD_NOT_ALLOWED.code)
                    .withHeader("Allow: " + getAcceptedMethods()).build();
        }
    }

    public String getAcceptedMethods() {
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

