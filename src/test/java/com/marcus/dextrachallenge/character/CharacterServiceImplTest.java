package com.marcus.dextrachallenge.character;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CharacterServiceImplTest {

    @Autowired
    private CharacterService characterService;

    @MockBean
    private CharacterRepository characterRepository;

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    @DisplayName("Test findById 1  - NotFound")
    void findByIdNotFound() {
        Mockito.doReturn(Optional.empty()).when(characterRepository).findById(1L);
        Optional<Character> optionalCharacter = characterService.findById(1L);
        Assertions.assertTrue(optionalCharacter.isEmpty());
    }

    @Test
    @DisplayName("Test findById 1  - Sucess")
    void findByIdSucess() {
        Character character = CharacterUtilTest.getHarryPotter();
        Mockito.doReturn(Optional.of(character)).when(characterRepository).findById(1L);
        Optional<Character> optionalCharacter = characterService.findById(1L);
        Assertions.assertNotNull(optionalCharacter);
        Assertions.assertEquals("Harry Potter", optionalCharacter.get().getName());
    }

    @Test
    void exists() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }
}
