package httpserver.handlers;

import httpserver.interfaces.IHandler;

import static httpserver.constants.HTTPLines.CRLF;

public class Options implements IHandler {

    private final String allowed;

    public Options(String methods) {
        this.allowed = methods;
    }

    @Override
    public String handle() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append("Allow: " + allowed + CRLF);
        response.append(CRLF);

        return response.toString();
    }

}
