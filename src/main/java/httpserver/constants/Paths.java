package httpserver.constants;

public enum Paths {
    SIMPLE_GET("/simple_get"),
    SIMPLE_GET_WITH_BODY("/simple_get_with_body"),
    HEAD_REQUEST("/head_request"),
    METHOD_OPTIONS("/method_options"),
    METHOD_OPTIONS2("/method_options2"),
    REDIRECT("/redirect"),
    ECHO_BODY("/echo_body"),
    TEXT_RESPONSE("/text_response"),
    JSON_RESPONSE("/json_response"),
    HTML_RESPONSE("/html_response"),
    XML_RESPONSE("/xml_response"),
    DOGGO_PNG("/doggo.png");


    public final String path;

    Paths(String uriPath) {
        this.path = uriPath;
    }
}
