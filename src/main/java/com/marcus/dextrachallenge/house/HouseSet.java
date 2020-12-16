package com.marcus.dextrachallenge.house;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;

@Data
public class HouseSet implements Serializable {
    private HashSet<House> houses = new HashSet<>();
}
