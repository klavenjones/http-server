package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;
import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;

public class HTMLResponse implements IHandler {
    private final StringBuilder response = new StringBuilder();
    private final String body;

    public HTMLResponse(String responseBody) {
        this.body = responseBody;
    }

    @Override
    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            response.append(DEFAULT_VERSION + SP + OK.code + CRLF);
        } else {
            response.append(DEFAULT_VERSION + SP +
                    METHOD_NOT_ALLOWED.code + CRLF);
        }
        response.append("Content-Type: text/html;charset=utf-8" + CRLF);
        response.append("Content-Length: " + this.body.length() + CRLF);
        response.append(CRLF);
        response.append(body + CRLF + CRLF);
        return response.toString();
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

    public enum AcceptedMethods {
        GET, HEAD, OPTIONS
    }
}
