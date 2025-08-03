package org.HttpServer;

import org.HttpServer.config.Configuration;
import org.HttpServer.config.ConfigurationManager;
import org.HttpServer.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    public final static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args)  {

        LOGGER.info("The server is running");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf= ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("Print Port" + conf.getPort());
        LOGGER.info("Print WebRoot" + conf.getWebroot());


        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort() , conf.getWebroot());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverListenerThread.start();
    }
}
