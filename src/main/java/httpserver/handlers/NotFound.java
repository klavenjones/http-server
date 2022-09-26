package httpserver.handlers;
import httpserver.response.Response;
import httpserver.response.StringResponse;
import httpserver.response.ResponseBuilder;

import static httpserver.constants.StatusCode.NOT_FOUND;

public class NotFound  {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    public Response handle() {
        return responseBuilder.withStatus(NOT_FOUND.code).build();
    }
}
