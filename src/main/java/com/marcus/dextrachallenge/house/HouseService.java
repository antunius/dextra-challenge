package com.marcus.dextrachallenge.house;

import com.marcus.dextrachallenge.exception.HouseNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface HouseService {
    Optional<House> getHouse(String id) throws HouseNotFoundException;

    Set<House> getAll();

}
