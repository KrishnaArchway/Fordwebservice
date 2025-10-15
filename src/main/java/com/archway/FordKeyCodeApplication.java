package com.archway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.archway.*"})
public class FordKeyCodeApplication extends SpringBootServletInitializer {
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(new Class[] { FordKeyCodeApplication.class });
  }
  
  public static void main(String[] args) {
    SpringApplication.run(FordKeyCodeApplication.class, args);
  }
}
