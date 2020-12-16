package com.marcus.dextrachallange.house;

import com.marcus.dextrachallange.exception.HouseNotFoundException;
import com.marcus.dextrachallange.integration.HouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class HouseServiceImpl implements HouseService {

    private final HouseClient houseClient;

    @Value("${apikey}")
    private String apiKey;

    private final HouseRepository houseRepository;

    public HouseServiceImpl(HouseClient houseClient, HouseRepository houseRepository) {
        this.houseClient = houseClient;
        this.houseRepository = houseRepository;
    }

    @Override
    public Optional<House> getHouse(String id) throws HouseNotFoundException {
        Optional<House> houseById = houseRepository.findHouseById(id);
        if (houseById.isPresent()) {
            return houseById;
        } else {
            try {
                House house = houseClient.getHouse(apiKey, id);
                houseRepository.save(house);
                return Optional.of(house);
            } catch (FeignException.NotFound notFound) {
                throw new HouseNotFoundException("house.not.found", id);
            }
        }
    }

    @Override
    public Set<House> getAll() {
        HashSet<House> houses;
        if (houseRepository.hasSaved()) {
            houses = houseRepository.getAll();
        } else {
            houses = houseClient.getHouses(apiKey).getHouses();
            houseRepository.saveAll(houses);
        }
        return houses;
    }

}
