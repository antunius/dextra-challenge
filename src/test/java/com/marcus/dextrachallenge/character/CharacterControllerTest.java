package com.marcus.dextrachallenge.character;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CharacterControllerTest {


    @MockBean
    private CharacterService characterService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("GET /character/ - Sucess")
    void findAllSucess() throws Exception {
        List<Character> characters = List.of(CharacterUtilTest.getHarryPotter(), CharacterUtilTest.getHermione());
        Mockito.doReturn(characters).when(characterService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/character/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is("Harry Potter")))
                .andExpect(jsonPath("$[1].name", Is.is("Hermione Granger")));
    }

    @Test
    @DisplayName("GET /character/ - NotFound")
    void findAllNotFound() throws Exception {
        Mockito.doReturn(Collections.emptyList()).when(characterService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/character/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[]"));
    }

    @Test
    @DisplayName("POST /character/ - Sucess")
    void save() throws Exception {
        Character character = CharacterUtilTest.getHarryPotter();
        Mockito.doReturn(character).when(characterService).save(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.post("/character")

                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(character)))

                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is("Harry Potter")));
    }

    private String asJsonString(Character character) {
        try {
            return new ObjectMapper().writeValueAsString(character);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("GET /character/1 - Sucess")
    void findOneSucess() throws Exception {
        Character character = CharacterUtilTest.getHarryPotter();
        Mockito.doReturn(character).when(characterService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/character/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is("Harry Potter")));
    }

    @Test
    @DisplayName("GET /character/1 - NotFound")
    void findOneNotFound() throws Exception {
        Mockito.doReturn(null).when(characterService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/character/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /character/exists/1 - Sucess")
    void existsSucess() throws Exception {
        Mockito.doReturn(true).when(characterService).exists(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/character/exists/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("GET /character/exists/1 - Not Found")
    void existsNotFound() throws Exception {
        Mockito.doReturn(false).when(characterService).exists(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/character/exists/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("false"));
    }


    @Test
    @DisplayName("DELETE /character/1 - Success")
    void deleteCharacterSucess() throws Exception {
        Mockito.doReturn(true).when(characterService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/character/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("DELETE /character/1 - Not Found")
    void deleteCharacterNotFound() throws Exception {
        Mockito.doReturn(false).when(characterService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/character/{id}", 1))
                .andExpect(content().string("false"));
    }

}
