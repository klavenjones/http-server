package httpserver.router;

import httpserver.handlers.EchoHandler;
import httpserver.handlers.NotAllowed;
import httpserver.handlers.NotFound;
import httpserver.handlers.Options;
import httpserver.handlers.OptionsTwo;
import httpserver.handlers.Redirect;
import httpserver.handlers.SimpleGet;
import httpserver.interfaces.IHandler;
import httpserver.request.Request;

import java.util.Map;

import static httpserver.constants.Paths.ECHO_BODY;
import static httpserver.constants.Paths.HEAD_REQUEST;
import static httpserver.constants.Paths.METHOD_OPTIONS;
import static httpserver.constants.Paths.METHOD_OPTIONS2;
import static httpserver.constants.Paths.REDIRECT;
import static httpserver.constants.Paths.SIMPLE_GET;
import static httpserver.constants.Paths.SIMPLE_GET_WITH_BODY;
import static java.util.Map.entry;

public class Router {
    private final Map<String, IHandler> routes = Map.ofEntries(
            entry(SIMPLE_GET.path, new SimpleGet()),
            entry(SIMPLE_GET_WITH_BODY.path, new SimpleGet("Hello world")),
            entry(HEAD_REQUEST.path, new NotAllowed()),
            entry(METHOD_OPTIONS.path, new Options()),
            entry(METHOD_OPTIONS2.path, new OptionsTwo()),
            entry(REDIRECT.path, new Redirect()),
            entry(ECHO_BODY.path, new EchoHandler("some body"))
    );


    public String handleRequest(Request request) {
        String path = request.path;

        if (!routes.containsKey(path)) {
            return handleNotFound().handle();
        }

        IHandler routeHandler = routes.get(path);
        return routeHandler.handle(request);
    }

    private NotFound handleNotFound() {
        return new NotFound();
    }
}
