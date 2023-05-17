package ca.jrvs.apps.twitter.spring;


import ca.jrvs.apps.twitter.TwitterCLIApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * `@SpringBootApplication` is a convenience annotation that adds all the following:
 *
 * - @Configation
 * - @EnableAutoConfiguration
 * - @ComponentScan
 * */

@SpringBootApplication
public class TwitterCLISpringboot implements CommandLineRunner {
    private TwitterCLIApp app;

    @Autowired
    public TwitterCLISpringboot(TwitterCLIApp app) { this.app = app; }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TwitterCLISpringboot.class);

        //Turn off web
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        app.run(args);
    }
}
