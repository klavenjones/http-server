package httpserver.response;

import httpserver.constants.StatusCode;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFUALT_VERSION;
import static httpserver.constants.HTTPLines.SP;


public class Response {
    public int statusCode;
    public String statusLine;

    public void buildResponse(StatusCode code) {
        this.statusCode = code.code;
        statusLine = DEFUALT_VERSION + SP + this.statusCode + SP + code + CRLF;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusLine() {
        return statusLine;
    }


}
