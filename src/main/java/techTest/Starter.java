package techTest;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * "jumpstart" class for spring boot,
 * launches the REST service MarketService
 * @author mitch
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Starter {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Starter.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        // handy debug.. 
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        
    }
}