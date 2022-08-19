package httpserver.response;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;

public class ResponseFormatter {
    public StringBuilder responseString = new StringBuilder();

    public String formatResponse(Response response) {
        responseString.append(DEFAULT_VERSION + SP + response.status + CRLF);
        if (response.headers != "") {
            responseString.append(response.headers + CRLF);
        } else {
            responseString.append(CRLF);
        }
        responseString.append(response.body);
        return responseString.toString();
    }
}
