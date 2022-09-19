package httpserver.response;

import static httpserver.constants.HTTPLines.CRLF;


public class ResponseBuilder {
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

    public Response build() {
        return new Response(status, headers, body);
    }
}
