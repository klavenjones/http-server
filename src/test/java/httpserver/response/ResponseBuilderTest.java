package httpserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseBuilderTest {
    private final ResponseBuilder responseBuilder = new ResponseBuilder();

    @Test
    @DisplayName("It should return an Ok status with Response")
    public void testIfBuilderReturnResponseWithOkStatus() {
        Response response = responseBuilder.build();
        assertEquals(response.status, "200 OK");
    }


}
