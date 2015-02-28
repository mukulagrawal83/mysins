package com.experiment.porter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * MAIN CLASS - Starts the application
 *
 * Following properties are required to be set
 *  porter.environment : (default:local) local/prod/test
 *  porter.db : (default:oracle) oracle/mysql
 *  porter.db.url : URL of the database
 *  porter.db.username : self explanatory
 *  porter.db.password : self explanatory
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.experiment.porter"})
public class PorterApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PorterApp.class, args);
    }
}
