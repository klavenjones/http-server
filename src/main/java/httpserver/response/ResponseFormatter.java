package httpserver.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static httpserver.constants.HTTPLines.CRLF;
import static httpserver.constants.HTTPLines.DEFAULT_VERSION;
import static httpserver.constants.HTTPLines.SP;

public class ResponseFormatter {
    public StringBuilder responseString = new StringBuilder();
    byte[] formattedResponse;

    public byte[] formatResponse(Response response) throws IOException {

        responseString.append(DEFAULT_VERSION + SP + response.status + CRLF);

        if (response.headers != "") {
            responseString.append(response.headers + CRLF);
        } else {
            responseString.append(CRLF);
        }

        formattedResponse = responseString.toString().getBytes();

        if (response.body == null) {
            return formattedResponse;
        }

        ByteArrayOutputStream responseWithBody = new ByteArrayOutputStream();
        responseWithBody.write(formattedResponse);
        responseWithBody.write(response.body);
        return responseWithBody.toByteArray();
    }
}
