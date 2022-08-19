package httpserver.response;

public class Response {
    public String status;
    public String headers;
    public String body;

    public Response(String status, String headers, String body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }
}
