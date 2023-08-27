package com.example.meireles.banker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan("com.example.meireles.banker")
public class BankerMultiModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankerMultiModuleApplication.class, args);
    }

}
