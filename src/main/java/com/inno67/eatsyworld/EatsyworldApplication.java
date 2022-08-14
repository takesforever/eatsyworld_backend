package com.inno67.eatsyworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EatsyworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatsyworldApplication.class, args);
    }

}
