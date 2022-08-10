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

import java.util.HashMap;


public class RunnableServer implements Runnable {
    public ISocket socketWrapper;
    public Request request;


    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
    }


    public String handleResponse(String route, String requestMethod) {
        switch (route) {
            case "/simple_get":
                IHandler simpleGet = new SimpleGet();
                return simpleGet.handle(request.getRequestMethod());
            case "/simple_get_with_body":
                IHandler simpleGetWithBody = new SimpleGet("Hello world");
                return simpleGetWithBody.handle(request.getRequestMethod());
            case "/redirect":
                IHandler redirect = new Redirect();
                return redirect.handle(request.getRequestMethod());
            case "/method_options":
                IHandler optionsHandler = new Options();
                return optionsHandler.handle(request.getRequestMethod());
            case "/method_options2":
                IHandler optionsHandlerTwo =
                        new OptionsTwo();
                return optionsHandlerTwo.handle(request.getRequestMethod());
            case "/head_request":
                IHandler methodsNotAllowed = new NotAllowed();
                return methodsNotAllowed.handle(request.getRequestMethod());
            case "/echo_body":
                IHandler echoHandler = new EchoHandler(
                        request.getRequestBody());
                return echoHandler.handle("POST");
            default:
                NotFound notFound = new NotFound();
                return notFound.handle();
        }
    }


    @Override
    public void run() {
        try {
            HashMap<String, String> headers = new HashMap<>();
            String clientMessage = socketWrapper.receiveData();
            if (clientMessage != null) {
                request = new Request(clientMessage);
                System.out.println("Client Connected: \n" + clientMessage);
                socketWrapper.sendData(
                        handleResponse(request.getRequestPath(),
                                request.getRequestMethod()));

            }


            socketWrapper.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
