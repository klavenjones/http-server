package httpserver.router;

import httpserver.handlers.NotFound;
import httpserver.interfaces.IHandler;
import httpserver.request.Request;

public class Router {

    private final Routes routes = new Routes();

    public String handleRequest(Request request) {
        String path = request.path;
        if (!routes.routeMap.containsKey(path)) {
            return handleNotFound().handle();
        }
        IHandler routeHandler = routes.routeMap.get(path);
        return routeHandler.handle(request);
    }


    private NotFound handleNotFound() {
        return new NotFound();
    }
}
