package httpserver.server;


import httpserver.interfaces.ISocket;
import httpserver.request.Request;
import httpserver.request.RequestParser;
import httpserver.response.Response;
import httpserver.response.ResponseFormatter;
import httpserver.router.Router;


public class RunnableServer implements Runnable {
    private final ISocket socketWrapper;
    private RequestParser requestParser;
    private final Router router;

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
                Response response = router.handleRequest(request);
                ResponseFormatter responseFormatter = new ResponseFormatter();
                socketWrapper.sendData(
                        responseFormatter.formatResponse(response));
            }
            socketWrapper.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
