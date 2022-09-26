package httpserver.response;

public class StringResponse extends Response{

    public String body;

    public StringResponse(String status, String headers, String body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }
}
