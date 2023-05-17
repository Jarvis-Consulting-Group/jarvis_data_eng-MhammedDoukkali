package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@ComponentScan(value = "ca.jrvs.apps.twitter")
public class TwitterCLIComponentScan {

    public static void main(String[] args) {
        //IoC container
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIComponentScan.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);

    }
}
