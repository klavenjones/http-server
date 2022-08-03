package httpserver.server;

import httpserver.interfaces.ISocket;

public class RunnableServer implements Runnable {

    public ISocket socketWrapper;

    public RunnableServer(ISocket socket) {
        this.socketWrapper = socket;
    }

    @Override
    public void run() {
        try {
            String clientMessage;
            while ((clientMessage = socketWrapper.receiveData()) != null) {
                socketWrapper.sendData(clientMessage);
            }
            socketWrapper.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
