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
public enum SuitEnum {

    HEART("HEART"), DIAMOND("DIAMOND"), CLUB("CLUB"), SPADE("SPADE");

    private final String name;
    private static final Map<String, SuitEnum> map = new HashMap<>();

    static {
        for (SuitEnum value : SuitEnum.values()) {
            map.put(value.name, value);
        }
    }

    private SuitEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static SuitEnum fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }
}
