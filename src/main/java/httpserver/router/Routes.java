package httpserver.router;

import httpserver.handlers.DoggoPNG;
import httpserver.handlers.EchoHandler;
import httpserver.handlers.HTMLHandler;
import httpserver.handlers.JSONHandler;
import httpserver.handlers.NotAllowed;
import httpserver.handlers.Options;
import httpserver.handlers.OptionsTwo;
import httpserver.handlers.Redirect;
import httpserver.handlers.SimpleGet;
import httpserver.handlers.TextHandler;
import httpserver.handlers.XMLHandler;
import httpserver.interfaces.IHandler;

import java.util.Map;

import static httpserver.constants.Paths.ECHO_BODY;
import static httpserver.constants.Paths.HEAD_REQUEST;
import static httpserver.constants.Paths.HTML_RESPONSE;
import static httpserver.constants.Paths.JSON_RESPONSE;
import static httpserver.constants.Paths.METHOD_OPTIONS;
import static httpserver.constants.Paths.METHOD_OPTIONS2;
import static httpserver.constants.Paths.PNG_RESPONSE;
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
                    entry(TEXT_RESPONSE.path, new TextHandler()),
                    entry(JSON_RESPONSE.path, new JSONHandler()),
                    entry(HTML_RESPONSE.path, new HTMLHandler()),
                    entry(XML_RESPONSE.path, new XMLHandler()),
                    entry(PNG_RESPONSE.path, new DoggoPNG())
            );
}
