package com.zaynukov.dev.angeldiary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class MyApplication {

    private static Logger logger = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {



        ApplicationContext context = SpringApplication.run(MyApplication.class, args);


        String url = "http://127.0.0.1:8080";

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                logger.error(e.getClass().getName(), e);
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open", new String[]{url});

            } catch (IOException e1) {
                try {
                    Runtime.getRuntime().exec("cmd.exe /c start chrome \"" + url + "\"");
                } catch (IOException e2) {
                    logger.error("IOException", e2);
                    System.exit(-1);
                }
            }

        }
    }

}
