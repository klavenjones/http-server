package httpserver.server;


import httpserver.interfaces.ISocket;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.router.Router;


public class RunnableServer implements Runnable {
    private ISocket socketWrapper;
    private RequestParser requestParser;
    private Router router;

    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
        this.router = new Router();
    }


    @Override
    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            System.out.println("Client Connected: " + clientMessage);
            if (clientMessage != null && clientMessage != "") {
                requestParser = new RequestParser(clientMessage);
                Request request = requestParser.parse();
                socketWrapper.sendData(router.handleRequest(request));
            }
            socketWrapper.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
