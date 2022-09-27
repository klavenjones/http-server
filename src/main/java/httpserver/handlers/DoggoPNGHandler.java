package httpserver.handlers;

import httpserver.interfaces.IHandler;
import httpserver.request.Request;
import httpserver.response.Response;
import httpserver.response.ResponseBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static httpserver.constants.StatusCode.METHOD_NOT_ALLOWED;
import static httpserver.constants.StatusCode.OK;

public class DoggoPNGHandler implements IHandler {

    private final ResponseBuilder responseBuilder = new ResponseBuilder();
    private String absolutePath = "web/doggo.png";

    @Override
    public Response handle(Request request) {
        try {
            if (isMethodAllowed(request.method)) {
                byte[] body = readFileData();
                return responseBuilder.withStatus(OK.code)
                        .withHeader("Allow: " + getAcceptedMethods())
                        .withHeader("Content-Type: image/png")
                        .withHeader("Content-Length: " + body.length)
                        .withBody(body).build();
            } else {
                return responseBuilder.withStatus(METHOD_NOT_ALLOWED.code)
                        .withHeader("Allow: " + getAcceptedMethods()).build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(method)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getAcceptedMethods() {
        List<String> methods = new LinkedList<>();
        for (AcceptedMethods acceptedMethod : AcceptedMethods.values()) {
            methods.add(acceptedMethod.name());
        }
        return String.join(", ", methods);
    }

    private byte[] readFileData() throws IOException {
        File file = new File(absolutePath);
        Path path = file.toPath();
        return Files.readAllBytes(path);
    }

    public enum AcceptedMethods {
        GET
    }
}
