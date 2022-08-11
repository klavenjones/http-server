package httpserver.response;

public class ResponseBuilder {
    private String status = "200 OK";
    private String body = "";
    private String headers = "";

    public ResponseBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder withHeader(String header) {
        this.headers = header;
        return this;
    }

    public ResponseBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public Response build() {
        return new Response(this.status, this.headers, this.body);
    }
}
