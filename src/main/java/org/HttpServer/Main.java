package org.HttpServer;

import org.HttpServer.config.Configuration;
import org.HttpServer.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf= ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Print Port" + conf.getPort());
        System.out.println("Print WebRoot" + conf.getWebroot());

        try {
            ServerSocket serverSocket= new ServerSocket(conf.getPort());
            Socket socket=serverSocket.accept();
            InputStream inputStream= socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();


            String html = "<html><head><title>This is a custom Http server</title></head><body><h1>This page is served using a custom http server</h1></body></html> ";
            final String CRLF=  "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: "+ html.getBytes().length + CRLF +
                    CRLF+ html + CRLF + CRLF;
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
