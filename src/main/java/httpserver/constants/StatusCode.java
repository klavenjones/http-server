package httpserver.constants;

public enum StatusCode {
    OK("200 OK"),
    NOT_FOUND("404 Not Found"),
    Moved_Permanently("301 Moved Permanently");

    public final String code;

    StatusCode(String statusCode) {
        this.code = statusCode;
    }

}
