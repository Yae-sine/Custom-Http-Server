package org.HttpServer;

import org.HttpServer.config.Configuration;
import org.HttpServer.config.ConfigurationManager;

public class Main {
    public static void main(String[] args){
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf= ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Print Port" + conf.getPort());
        System.out.println("Print WebRoot" + conf.getWebroot());


    }
}
