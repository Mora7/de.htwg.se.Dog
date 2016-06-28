/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kev
 */
public enum ValueEnum {

    ACE("A", 1, 11), TWO("2", 2, 0), THREE("3", 3, 0), FOUR("4", 4, -4),
    FIVE("5", 5, 0), SIX("6", 6, 0), SEVEN("7", 7, 0), EIGHT("8", 8, 0),
    NINE("9", 9, 0), TEN("10", 10, 0), JACK("J", 0, 0), QUEEN("Q", 12, 0), KING("K", 13, 0), JOKER("JOKER", 0, 0);

    private final String name;
    private final int i1;
    private final int i2;
    private static final Map<String, ValueEnum> map = new HashMap<>();

    static {
        for (ValueEnum value : ValueEnum.values()) {
            map.put(value.name, value);
        }
    }

    private ValueEnum(String name, int i1, int i2) {
        this.name = name;
        this.i1 = i1;
        this.i2 = i2;
    }

    public int getI1() {
        return i1;
    }

    public int getI2() {
        return i2;
    }

    public String getName() {
        return this.name;
    }

    public static ValueEnum fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }
}
