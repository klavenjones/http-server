package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.response.Response;
import httpserver.response.ResponseBuilder;

import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;

public class HTMLResponse implements IHandler {
    private final ResponseBuilder responseBuilder = new ResponseBuilder();


    @Override
    public Response handle(Request request) {

        String body = "<html><body><p>HTML Response</p></body></html>";

        if (isMethodAllowed(request.method)) {
            return responseBuilder.withStatus(OK.code)
                    .withHeader("Allow: " + getMethods())
                    .withHeader("Content-Type: text/html;charset=utf-8")
                    .withHeader("Content-Length: " + body.length())
                    .withBody(body).build();
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
        GET, HEAD, OPTIONS
    }
}
