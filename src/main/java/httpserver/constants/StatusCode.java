package httpserver.constants;

public enum StatusCode {
    OK(200),
    NOT_FOUND(404),
    Moved_Permanently(301);

    public final int code;

    StatusCode(int statusCode) {
        this.code = statusCode;
    }

}
