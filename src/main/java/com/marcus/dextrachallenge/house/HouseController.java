package com.marcus.dextrachallenge.house;

import com.marcus.dextrachallenge.exception.HouseNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("house")
@OpenAPIDefinition
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("{id}")
    public House getHouse(@PathVariable String id) throws HouseNotFoundException {
        return houseService.getHouse(id).get();
    }

    @GetMapping
    public Set<House> getAllHouses() {
        return houseService.getAll();
    }
}
