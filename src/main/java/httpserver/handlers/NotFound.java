package httpserver.handlers;
import static httpserver.constants.HTTPLines.*;
import static httpserver.constants.StatusCode.NOT_FOUND;

public class NotFound  {
    public String handle() {
        StringBuilder response = new StringBuilder();
        response.append(DEFAULT_VERSION + SP + NOT_FOUND.code + CRLF);
        response.append(CRLF);
        return response.toString();
    }
}
