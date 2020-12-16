package com.marcus.dextrachallange.house;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class House implements Serializable {
    private String id;
    private String name;
    private String headOfHouse;
    private Set<String> values = new HashSet<>();
    private Set<String> colors = new HashSet<>();
    private String school;
    private String mascot;
    private String houseGhost;
    private String founder;

}
