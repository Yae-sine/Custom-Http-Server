package org.HttpServer.core;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class HttpConnectionWorkerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;
    public HttpConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        InputStream inputStream= null;
        OutputStream outputStream = null;
        try {
            inputStream= socket.getInputStream();
            outputStream = socket.getOutputStream();


            String html = "<html><head><title>This is a custom Http server</title></head><body><h1>This page is served using a custom http server</h1></body></html> ";
            final String CRLF=  "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                            "Content-Length: "+ html.getBytes().length + CRLF +
                            CRLF+ html + CRLF + CRLF;
            outputStream.write(response.getBytes());
            LOGGER.info("Processing finished");
        } catch (IOException e) {
            LOGGER.error("Problem with communication" , e);
            throw new RuntimeException(e);
        }
        finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
