package httpserver.handlers;

import httpserver.TestUtils;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static httpserver.constants.HTTPLines.CRLF;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLResponseTest {

    String xmlResponse = "HTTP/1.1 200 OK" + CRLF
            + "Content-Type: application/xml;charset=utf-8" + CRLF
            + "Content-Length: 38" + CRLF + CRLF +
            "<note><body>XML Response</body></note>";

    @Test
    @DisplayName("Should return the correct response with body")
    public void testIfHandlerReturnsCorrectResponse() {
        XMLResponse xmlHandler =
                new XMLResponse("<note><body>XML Response</body></note>");
        RequestParser requestParser =
                new RequestParser(TestUtils.dummyGetData("xml_response"));
        Request request = requestParser.parse();
        assertEquals(xmlHandler.handle(request),
                xmlResponse);
    }


}
