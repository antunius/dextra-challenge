package com.marcus.dextrachallange.character;

import com.marcus.dextrachallange.exception.HouseNotFoundException;
import com.marcus.dextrachallange.framework.service.JpaServiceImpl;
import com.marcus.dextrachallange.house.House;
import com.marcus.dextrachallange.house.HouseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterServiceImpl extends JpaServiceImpl<Character, CharacterFilter> implements CharacterService {
    private final CharacterRepository characterRepository;

    private final HouseService houseService;

    public CharacterServiceImpl(CharacterRepository characterRepository, HouseService houseService) {
        this.characterRepository = characterRepository;
        this.houseService = houseService;
    }

    @Override
    public <S extends Character> void preSave(S entity) throws HouseNotFoundException {
        Optional<House> house = houseService.getHouse(entity.getHouse());
        if (house.isPresent()) {
            super.preSave(entity);
        }

    }

    @Override
    protected JpaRepository<Character, Long> getData() {
        return characterRepository;
    }
}
