package httpserver.constants;

public enum Paths {
    SIMPLE_GET("/simple_get"),
    SIMPLE_GET_WITH_BODY("/simple_get_with_body"),
    HEAD_REQUEST("/head_request"),
    METHOD_OPTIONS("/method_options"),
    METHOD_OPTIONS2("/method_options2"),
    REDIRECT("/redirect"),
    ECHO_BODY("/echo_body");


    public final String path;

    Paths(String uriPath) {
        this.path = uriPath;
    }
}
