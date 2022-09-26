package httpserver.response;

public class Response {
    public String status;
    public String headers;
    public byte[] body;

    public Response(String status, String headers, byte[] body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }
}
