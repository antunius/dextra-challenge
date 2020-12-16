package com.marcus.dextrachallange.character;

public class CharacterUtilTest {
    public static Character getHarryPotter() {
        return Character
                .builder()
                .id(1L)
                .name("Harry Potter")
                .house("Grifinory")
                .animagus("Doby")
                .patronus("Cervo")
                .ministryOfMagic(false)
                .dumbledoresArmy(true)
                .school("Hogwarts")
                .build();
    }

    public static Character getHermione() {
        return Character
                .builder()
                .name("Hermione Granger")
                .house("Grifinory")
                .animagus("Doby")
                .patronus("Cervo")
                .ministryOfMagic(false)
                .dumbledoresArmy(true)
                .school("Hogwarts")
                .build();
    }
}
