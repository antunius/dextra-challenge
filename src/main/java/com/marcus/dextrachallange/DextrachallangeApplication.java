package com.marcus.dextrachallange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DextrachallangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DextrachallangeApplication.class, args);
    }

}
