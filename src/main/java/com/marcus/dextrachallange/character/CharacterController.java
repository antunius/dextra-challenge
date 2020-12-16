package com.marcus.dextrachallange.character;


import com.marcus.dextrachallange.framework.controller.RestCrudController;
import com.marcus.dextrachallange.framework.service.JpaService;
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
