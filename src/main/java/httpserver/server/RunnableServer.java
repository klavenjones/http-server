package httpserver.server;


import httpserver.handlers.Options;
import httpserver.handlers.Redirect;
import httpserver.handlers.SimpleGet;
import httpserver.interfaces.IHandler;
import httpserver.interfaces.ISocket;
import httpserver.request.Request;

import java.util.HashMap;

import static httpserver.constants.HTTPLines.CRLF;


public class RunnableServer implements Runnable {
    public ISocket socketWrapper;
    public Request request;


    public String method;
    public String path;
    public String body;

    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
    }


    public String dummyAllowMoreMethodResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append("Allow: OPTIONS, GET, HEAD, PUT, POST" + CRLF);
        response.append(CRLF);

        return response.toString();
    }

    public String dummyMethodNotAllowedResponse(String requestMethod) {
        StringBuilder response = new StringBuilder();
        if (!requestMethod.equals("HEAD")) {
            response.append("HTTP/1.1 405 Method Not Allowed" + CRLF);
        } else {
            response.append("HTTP/1.1 200 OK" + CRLF);
        }
        response.append("Allow: HEAD, OPTIONS" + CRLF);
        response.append(CRLF);

        return response.toString();
    }

    public String dummyNotFoundResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 404 Not Found" + CRLF);
        response.append(CRLF);
        return response.toString();
    }


    public String dummyEcho() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append(CRLF);
        response.append(request.getRequestBody());
        return response.toString();
    }


    public String handleResponse(String route, String requestMethod) {
        switch (route) {
            case "/simple_get":
                IHandler simpleGet = new SimpleGet();
                return simpleGet.handle();
            case "/simple_get_with_body":
                IHandler simpleGetWithBody = new SimpleGet("Hello world");
                return simpleGetWithBody.handle();
            case "/redirect":
                IHandler redirect = new Redirect();
                return redirect.handle();
            case "/method_options":
                IHandler optionsHandler = new Options("OPTIONS, GET, HEAD");
                return optionsHandler.handle();
            case "/method_options2":
                IHandler optionsHandlerTwo =
                        new Options("OPTIONS, GET, HEAD, PUT, POST");
                return optionsHandlerTwo.handle();
            case "/head_request":
                Options optionsHandlerThree = new Options("OPTIONS, HEAD");
                return optionsHandlerThree.handle(request.getRequestMethod());
            case "/echo_body":
                return dummyEcho();
            default:
                return dummyNotFoundResponse();
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
