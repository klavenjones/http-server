package httpserver.request;
import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.SP;

public class Request {

    String incomingRequest;

    public Request(String incomingData) {
        this.incomingRequest = incomingData;
    }
    public String getRequestMethod(){
        return incomingRequest.split(SP)[0];
    }
    public String getRequestPath(){
        return incomingRequest.split(SP)[1];
    }
    public String getRequestVersion(){
        return incomingRequest.split(SP)[2];
    }
    public String getRequestBody(){
        return incomingRequest.split(CRLF, 2)[1];
    }

}
