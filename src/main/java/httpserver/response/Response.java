package httpserver.response;


public class Response {
    public String status;
    public String body;
    public String headers;


    public Response(String status, String body, String headers) {
        this.status = status;
        this.body = body;
        this.headers = headers;
    }
}
