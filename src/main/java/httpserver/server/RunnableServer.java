package httpserver.server;


import httpserver.interfaces.ISocket;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import httpserver.router.Router;

import java.io.IOException;


public class RunnableServer implements Runnable {
    private final ISocket socketWrapper;
    private final Router router;
    private RequestParser requestParser;

    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
        this.router = new Router();
    }

    public void parseClientMessage(String clientMessage, ISocket socketWrapper)
            throws IOException {

        if (clientMessage != null && !clientMessage.isEmpty()) {
            requestParser = new RequestParser(clientMessage);
            Request request = requestParser.parse();
            Response response = router.handleRequest(request);
            ResponseFormatter responseFormatter = new ResponseFormatter();
            socketWrapper.sendData(responseFormatter.formatResponse(response));
        }
    }

    @Override
    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            System.out.println("Client Connected: " + clientMessage);
            parseClientMessage(clientMessage, socketWrapper);
            socketWrapper.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
