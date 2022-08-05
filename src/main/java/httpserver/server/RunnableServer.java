package httpserver.server;


import httpserver.interfaces.ISocket;
import httpserver.request.Request;

import static httpserver.constants.HTTPLines.CRLF;


public class RunnableServer implements Runnable {
    private static final int SP = 0x20; // 32
    private static final int CR = 0x0D; // 13
    private static final int LF = 0x0A; // 10
    public ISocket socketWrapper;
    public Request request;


    public String method;
    public String path;
    public String body;

    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
    }

    public String dummyResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append(CRLF);

        return response.toString();
    }

    public String dummyWithBodyResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append(CRLF);
        response.append("Hello world");

        return response.toString();
    }

    public String dummyLocationResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 301 Moved Permanently" + CRLF);
        response.append("Location: http://127.0.0.1:5000/simple_get" + CRLF);
        response.append(CRLF);

        return response.toString();
    }


    public String dummyAllowThreeMethodsResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append("Allow: OPTIONS, GET, HEAD" + CRLF);
        response.append(CRLF);

        return response.toString();
    }

    public String dummyAllowMoreMethodResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK" + CRLF);
        response.append("Allow: OPTIONS, GET, HEAD, PUT, POST" + CRLF);
        response.append(CRLF);

        return response.toString();
    }

    public String dummyMethodNotAllowedResponse() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 405 Method Not Allowed" + CRLF);
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


    public String handleResponse(String route) {
        switch (route) {
            case "/simple_get":
                return dummyResponse();
            case "simple_get_with_body":
                return dummyWithBodyResponse();
            case "/redirect":
                return dummyLocationResponse();
            case "/method_options":
                return dummyAllowThreeMethodsResponse();
            case "/method_options2":
                return dummyAllowMoreMethodResponse();
            case "/head_request":
                return dummyMethodNotAllowedResponse();
            default:
                return dummyNotFoundResponse();
        }
    }


    @Override
    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            if (clientMessage != null) {
                request = new Request(clientMessage);
                System.out.println("This is the method of the request " +
                        request.getRequestMethod() + "\n");
                socketWrapper.sendData(
                        handleResponse(request.getRequestPath()));
            }


            socketWrapper.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
