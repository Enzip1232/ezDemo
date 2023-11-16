package com.enzip.ezdemo;

import com.enzip.robot.EnableBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBot
@SpringBootApplication
public class EzDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzDemoApplication.class, args);
    }

}
