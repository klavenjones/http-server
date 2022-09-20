package httpserver.interfaces;


import httpserver.request.Request;
import httpserver.response.Response;

public interface IHandler {
    Response handle(Request request);

    boolean isMethodAllowed(String method);

    String getMethods();
}
