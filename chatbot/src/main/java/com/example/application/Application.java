package com.example.application;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.alicebot.ab.Bot;

import org.alicebot.ab.configuration.BotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.helpers.LaunchUtil;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication

public class Application extends SpringBootServletInitializer {
    @Bean
    public Bot alice() {
    return new Bot(BotConfiguration.builder()
            .name("alice")
            .path("src/main/resources/bots/alice")
            .build()
    );
}
@Bean
public ScheduledExecutorService executorService() {
    return Executors.newScheduledThreadPool(2);
}
    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

}

