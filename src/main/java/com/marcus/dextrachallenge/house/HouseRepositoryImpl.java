package com.marcus.dextrachallenge.house;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class HouseRepositoryImpl implements HouseRepository {
    private final HashOperations<String, String, House> hashOperations;

    public HouseRepositoryImpl(RedisTemplate<String, House> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Optional<House> findHouseById(String id) {
        return Optional.ofNullable(hashOperations.get("HOUSE", id));
    }

    @Override
    public Set<House> saveAll(Set<House> houses) {
        Map<String, House> houseMap = houses.stream().collect(Collectors.toMap(House::getId, house -> house));
        hashOperations.putAll("HOUSE", houseMap);
        return houses;
    }

    @Override
    public House save(House house) {
        hashOperations.put("HOUSE",house.getId(),house);
        return house;
    }

    @Override
    public boolean exists(String id) {
        return hashOperations.hasKey("HOUSE", id);
    }

    @Override
    public boolean hasSaved() {
        return !hashOperations.keys("HOUSE").isEmpty();
    }

    @Override
    public HashSet<House> getAll() {
        return new HashSet<>(hashOperations.values("HOUSE"));
    }
}
