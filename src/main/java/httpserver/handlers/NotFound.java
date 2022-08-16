package httpserver.handlers;
import httpserver.response.ResponseBuilder;

import static httpserver.constants.HTTPLines.*;
import static httpserver.constants.StatusCode.NOT_FOUND;

public class NotFound  {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    public String handle() {
       return responseBuilder.withStatus(NOT_FOUND.code).build();
    }
}
