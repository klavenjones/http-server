package httpserver.response;

import static httpserver.constants.HTTPLines.CRLF;


public class ResponseBuilder<T> {
    private String status;
    private String stringBody = "";
    private byte[] byteBody;
    private String headers = "";

    private Response stringResponse;
    private Response byteResponse;

    public ResponseBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder withHeader(String header) {
        this.headers += header + CRLF;
        return this;
    }

    public ResponseBuilder withBody(T body) {
        if(body instanceof String){
            this.stringBody = body.toString();
        } else if(body instanceof byte[]) {
            this.byteBody = (byte[]) body;
        } else {
            throw new RuntimeException("Not the proper data type");
        }
        return this;
    }



    public Response build() {
        if (this.byteBody.length > 0) {
            return new ByteResponse(status, headers, byteBody);
        } else if (this.stringBody.length() > 0) {
            return new StringResponse(status, headers, stringBody);
        }
        throw new RuntimeException("Error");
    }
}
