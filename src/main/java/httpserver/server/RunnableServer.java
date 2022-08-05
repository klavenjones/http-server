package httpserver.server;

import httpserver.interfaces.ISocket;
import httpserver.request.Request;


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






    @Override
    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            if(clientMessage != null){
                request = new Request(clientMessage);
               System.out.println("This is the method of the request " + request.getRequestMethod() + "\n");


            }



            socketWrapper.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
