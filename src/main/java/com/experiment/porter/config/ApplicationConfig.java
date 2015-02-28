package com.experiment.porter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Configuration class
 */
@Configuration
public class ApplicationConfig {

    Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    @Value("${porter.environment:local}")
    String environment;

    /**
     * Environment variable set as the application directory
     */
    @Value("${user.dir}")
    String userDirectory;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        JettyEmbeddedServletContainerFactory jetty = new JettyEmbeddedServletContainerFactory();
        jetty.setContextPath("/porter");
        jetty.setPort(8272);
        log.debug("Environment setup for : " + environment);
        try {
            String documentRoot;
            if ("local".equals(environment)) {
                documentRoot = userDirectory + File.separator + "src//main//resources";
            } else {
                documentRoot = userDirectory + File.separator + "porterApp.jar";
            }
            log.debug("Setting documentRoot to {}", documentRoot);
            jetty.setDocumentRoot(new File(documentRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jetty;
    }

}
