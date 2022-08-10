package httpserver.interfaces;

import httpserver.request.Request;

public interface IHandler {
    String handle(Request request);
    boolean isMethodAllowed(String method);
}
