package com.marcus.dextrachallenge.character;


import com.marcus.dextrachallenge.framework.controller.RestCrudController;
import com.marcus.dextrachallenge.framework.service.JpaService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("character")
@OpenAPIDefinition
public class CharacterController extends RestCrudController<Character, CharacterFilter> {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Override
    protected JpaService<Character, CharacterFilter> getService() {
        return characterService;
    }
}
