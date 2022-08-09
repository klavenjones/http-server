package httpserver.interfaces;

public interface IHandler {
    String handle(String method);
    boolean isMethodAllowed(String method);
}
