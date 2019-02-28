package com.zaynukov.dev.angeldiary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class MyApplication {

    private final static Logger logger = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);

        String url = "http://localhost:7979";
        openOnDefaultBrowser(url);
    }

    private static void openOnDefaultBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
                return;
            } catch (IOException | URISyntaxException e) {
                logger.error(e.getClass().getName(), e);
            }
        }


        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("xdg-open", new String[]{url});
        } catch (IOException e1) {
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e2) {
                logger.error("IOException", e1);
                logger.error("IOException", e2);
//                System.exit(-1);
            }
        }

    }

}
