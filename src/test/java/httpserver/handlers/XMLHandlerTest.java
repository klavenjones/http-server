package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLHandlerTest {

    String xmlResponseText = "HTTP/1.1 200 OK" + CRLF
            + "Allow: GET, HEAD, OPTIONS" + CRLF
            + "Content-Type: application/xml;charset=utf-8" + CRLF
            + "Content-Length: 38" + CRLF + CRLF +
            "<note><body>XML Response</body></note>";

    @Test
    @DisplayName("Should return the correct response with body")
    public void testIfHandlerReturnsCorrectResponse() {
        XMLHandler xmlHandler = new XMLHandler();
        RequestParser requestParser =
                new RequestParser(TestUtils.mockGetData("xml_response"));
        Request request = requestParser.parse();
        Response response = xmlHandler.handle(request);
        ResponseFormatter responseFormatter = new ResponseFormatter();

        String xmlResponse =
                responseFormatter.formatResponse(response);

        assertEquals(xmlResponse, xmlResponseText);
    }


}
