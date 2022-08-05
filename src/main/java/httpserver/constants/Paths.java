package httpserver.constants;

public enum Paths {
    SIMPLE_GET("/simple_get");
    public final String path;

    Paths(String path) {
        this.path = path;
    }
}
