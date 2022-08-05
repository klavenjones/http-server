package httpserver.constants;

public enum Paths {
    SIMPLE_GET("/simple_get");
    public final String path;

    Paths(String uriPath) {
        this.path = uriPath;
    }
}
