package org.HttpServer.core;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.HttpServer.Main.LOGGER;

public class ServerListenerThread extends Thread{
    private int port;
    private String webRoot;
    private ServerSocket serverSocket;
    public ServerListenerThread(int port , String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket= new ServerSocket(this.port);

    }
    @Override
    public void run(){

        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()){
            Socket socket=serverSocket.accept();
            LOGGER.info("Connection accepted " + socket.getInetAddress());
            HttpConnectionWorkerThread httpConnectionWorkerThread = new HttpConnectionWorkerThread(socket);
            httpConnectionWorkerThread.start();
            }

        } catch (IOException e) {
            LOGGER.error("Error while setting up the socket" , e);
            throw new RuntimeException(e);
        }
        finally {
            if (serverSocket!= null){
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
