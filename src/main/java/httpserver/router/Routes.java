package httpserver.router;

import httpserver.handlers.DoggoPNGHandler;
import httpserver.handlers.EchoHandler;
import httpserver.handlers.HTMLResponseHandler;
import httpserver.handlers.HealthCheckHandler;
import httpserver.handlers.JSONResponseHandler;
import httpserver.handlers.KissesGIFHandler;
import httpserver.handlers.KittenJPGHandler;
import httpserver.handlers.NotAllowed;
import httpserver.handlers.Options;
import httpserver.handlers.OptionsTwo;
import httpserver.handlers.Redirect;
import httpserver.handlers.SimpleGet;
import httpserver.handlers.TextResponseHandler;
import httpserver.handlers.XMLResponseHandler;
import httpserver.interfaces.IHandler;

import java.util.Map;

import static httpserver.constants.Paths.DOGGO_PNG;
import static httpserver.constants.Paths.ECHO_BODY;
import static httpserver.constants.Paths.HEAD_REQUEST;
import static httpserver.constants.Paths.HEALTH_CHECK_HTML;
import static httpserver.constants.Paths.HTML_RESPONSE;
import static httpserver.constants.Paths.JSON_RESPONSE;
import static httpserver.constants.Paths.KISSES_GIF;
import static httpserver.constants.Paths.KITTEN_JPG;
import static httpserver.constants.Paths.METHOD_OPTIONS;
import static httpserver.constants.Paths.METHOD_OPTIONS2;
import static httpserver.constants.Paths.REDIRECT;
import static httpserver.constants.Paths.SIMPLE_GET;
import static httpserver.constants.Paths.SIMPLE_GET_WITH_BODY;
import static httpserver.constants.Paths.TEXT_RESPONSE;
import static httpserver.constants.Paths.XML_RESPONSE;
import static java.util.Map.entry;

public class Routes {

    public final Map<String, IHandler> routeMap =
            Map.ofEntries(entry(SIMPLE_GET.path, new SimpleGet()),
                    entry(SIMPLE_GET_WITH_BODY.path, new SimpleGet()),
                    entry(HEAD_REQUEST.path, new NotAllowed()),
                    entry(METHOD_OPTIONS.path, new Options()),
                    entry(METHOD_OPTIONS2.path, new OptionsTwo()),
                    entry(REDIRECT.path, new Redirect()),
                    entry(ECHO_BODY.path, new EchoHandler()),
                    entry(TEXT_RESPONSE.path, new TextResponseHandler()),
                    entry(JSON_RESPONSE.path, new JSONResponseHandler()),
                    entry(HTML_RESPONSE.path, new HTMLResponseHandler()),
                    entry(XML_RESPONSE.path, new XMLResponseHandler()),
                    entry(DOGGO_PNG.path, new DoggoPNGHandler()),
                    entry(KISSES_GIF.path, new KissesGIFHandler()),
                    entry(HEALTH_CHECK_HTML.path, new HealthCheckHandler()),
                    entry(KITTEN_JPG.path, new KittenJPGHandler()));
}
