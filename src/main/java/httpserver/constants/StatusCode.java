package httpserver.constants;

public enum StatusCode {
    OK("200 OK"),
    NOT_FOUND("404 Not Found"),
    MOVED_PERMANENTLY("301 Moved Permanently"),
    METHOD_NOT_ALLOWED("405 Method Not Allowed");

    public final String code;

    StatusCode(String statusCode) {
        this.code = statusCode;
    }

}
