package com.marcus.dextrachallange.integration;

import com.marcus.dextrachallange.config.HouseClientConfiguration;
import com.marcus.dextrachallange.house.House;
import com.marcus.dextrachallange.house.HouseSet;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "house", url = "http://us-central1-rh-challenges.cloudfunctions.net/potterApi", name = "house", configuration = HouseClientConfiguration.class)
public interface HouseClient {

    @GetMapping(value = "/houses")
    @Headers("Content-Type: application/json")
    @ResponseBody
    HouseSet getHouses(@RequestHeader("apikey") String tokenApi);

    @GetMapping(value = "/houses/{id}")
    @Headers("Content-Type: application/json")
    @ResponseBody
    House getHouse(@RequestHeader("apikey") String tokeApi, @PathVariable("id") String id);
}

