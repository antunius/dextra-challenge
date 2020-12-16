package com.marcus.dextrachallange.character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity(name = "character_hp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{name.empty}")
    private String name;

    private String house;
    private String patronus;
    private String species;
    private String bloodStatus;
    private String role;
    private String school;
    private boolean deathEater;
    private boolean dumbledoresArmy;
    private boolean orderOfThePhoenix;
    private boolean ministryOfMagic;
    private String alias;
    private String wand;
    private String boggart;
    private String animagus;

    public Character(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
