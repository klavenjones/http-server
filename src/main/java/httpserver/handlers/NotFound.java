package httpserver.handlers;
import static httpserver.constants.HTTPLines.CRLF;

public class NotFound  {
    public String handle() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 404 Not Found" + CRLF);
        response.append(CRLF);
        return response.toString();
    }
}
