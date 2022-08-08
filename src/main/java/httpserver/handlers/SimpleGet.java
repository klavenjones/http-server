package httpserver.handlers;

import httpserver.interfaces.IHandler;

import static httpserver.constants.HTTPLines.CRLF;

public class SimpleGet implements IHandler {

    private final String body;

    public SimpleGet(String responseBody) {
        this.body = responseBody;
    }

    public SimpleGet() {
        this.body = "";
    }

    @Override
    public String handle() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append(CRLF);
        response.append(body);

        return response.toString();
    }
}
