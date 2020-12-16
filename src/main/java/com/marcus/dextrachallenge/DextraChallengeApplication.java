package com.marcus.dextrachallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DextraChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DextraChallengeApplication.class, args);
    }

}
