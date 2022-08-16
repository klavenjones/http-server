package httpserver.response;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;


public class ResponseBuilder {
    public StringBuilder response = new StringBuilder();
    private String status;
    private String body = "";
    private String headers = "";

    public ResponseBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder withHeader(String header) {
        this.headers += header + CRLF;
        return this;
    }

    public ResponseBuilder withBody(String body) {
        this.body = body;
        return this;
    }


    public String build() {
        this.response.append(DEFAULT_VERSION + SP + status + CRLF);
        if (headers != "") {
            this.response.append(headers + CRLF);
        }
        this.response.append(body);
        return response.toString();
    }
}
