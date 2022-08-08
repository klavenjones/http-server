package httpserver.handlers;

import httpserver.interfaces.IHandler;

import static httpserver.constants.HTTPLines.CRLF;

public class Redirect implements IHandler {
    @Override
    public String handle() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 301 Moved Permanently" + CRLF);
        response.append("Location: http://127.0.0.1:5000/simple_get" + CRLF);
        response.append(CRLF);

        return response.toString();
    }
}
