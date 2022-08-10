package httpserver.server;


import httpserver.handlers.EchoHandler;
import httpserver.handlers.NotAllowed;
import httpserver.handlers.NotFound;
import httpserver.handlers.Options;
import httpserver.handlers.OptionsTwo;
import httpserver.handlers.Redirect;
import httpserver.handlers.SimpleGet;
import httpserver.interfaces.IHandler;
import httpserver.interfaces.ISocket;
import httpserver.request.Request;
import httpserver.request.RequestParser;


public class RunnableServer implements Runnable {
    public ISocket socketWrapper;
    public RequestParser requestParser;


    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
    }


    public String handleResponse(Request request) {
        switch (request.path) {
            case "/simple_get":
                IHandler simpleGet = new SimpleGet();
                return simpleGet.handle(request);
            case "/simple_get_with_body":
                IHandler simpleGetWithBody = new SimpleGet("Hello world");
                return simpleGetWithBody.handle(request);
            case "/redirect":
                IHandler redirect = new Redirect();
                return redirect.handle(request);
            case "/method_options":
                IHandler optionsHandler = new Options();
                return optionsHandler.handle(request);
            case "/method_options2":
                IHandler optionsHandlerTwo =
                        new OptionsTwo();
                return optionsHandlerTwo.handle(request);
            case "/head_request":
                IHandler methodsNotAllowed = new NotAllowed();
                return methodsNotAllowed.handle(request);
            case "/echo_body":
                IHandler echoHandler = new EchoHandler(request.body);
                return echoHandler.handle(request);
            default:
                NotFound notFound = new NotFound();
                return notFound.handle();
        }
    }


    @Override
    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            if (clientMessage != null) {
                System.out.println("Client Connected: " + clientMessage + "\n");
                requestParser = new RequestParser(clientMessage);
                Request request = requestParser.parse();
                socketWrapper.sendData(handleResponse(request));

            }


            socketWrapper.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
