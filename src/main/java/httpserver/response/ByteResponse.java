package httpserver.response;

public class ByteResponse extends Response{

    public byte[] body;

    public ByteResponse(String status, String headers, byte[] body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }
}
