package com.dev.estacio.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dev.estacio.finance")
public class AppFinance {

    public static void main(String[] args) {
        SpringApplication.run(AppFinance.class, args);
    }

}
