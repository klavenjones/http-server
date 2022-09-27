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

        //Build Status Line
        responseString.append(DEFAULT_VERSION + SP + response.status + CRLF);

        //If there are any headers, then add it to the response string.
        // If not add a line break to the response string
        if (response.headers != "") {
            responseString.append(response.headers + CRLF);
        } else {
            responseString.append(CRLF);
        }

        //Convert the response string to bytes
        formattedResponse = responseString.toString().getBytes();

        //If there is no body to add in the response,
        // return the formatted response.
        if (response.body == null) {
            return formattedResponse;
        }

        //If there is a body, build a byte array
        // and add the formattedResonse and
        // body to the byte array and return it.
        ByteArrayOutputStream responseWithBody = new ByteArrayOutputStream();
        responseWithBody.write(formattedResponse);
        responseWithBody.write(response.body);
        return responseWithBody.toByteArray();
    }
}
