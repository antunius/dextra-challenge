package com.marcus.dextrachallenge.house;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface HouseRepository {

    Optional<House> findHouseById(String id);

    Set<House> saveAll(Set<House> houses);

    House save(House house);

    HashSet<House> getAll();

    boolean hasSaved();

    boolean exists(String id);
}
