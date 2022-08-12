package httpserver.server;


import httpserver.interfaces.ISocket;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.router.Router;


public class RunnableServer implements Runnable {
    public ISocket socketWrapper;
    public RequestParser requestParser;


    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
    }


    @Override
    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            if (clientMessage != null) {
                requestParser = new RequestParser(clientMessage);
                Request request = requestParser.parse();
                Router router = new Router();
                socketWrapper.sendData(router.handleRequest(request));

            }


            socketWrapper.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
