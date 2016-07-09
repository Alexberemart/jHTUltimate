package com.alexberemart.jhtultimate.model.enums;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum PositionLevelTwo {

    KE(1, "prueba", PositionLevelOne.K),
    DC(2, "prueba", PositionLevelOne.D),
    DL(3, "prueba", PositionLevelOne.D),
    MC(4, "prueba", PositionLevelOne.M),
    ML(5, "prueba", PositionLevelOne.M),
    AC(6, "prueba", PositionLevelOne.A);

    protected Integer value;
    protected String description;
    protected PositionLevelOne positionLevelOne;

    PositionLevelTwo(Integer code, String description, PositionLevelOne positionLevelOne) {
        this.value = code;
        this.description = description;
        this.positionLevelOne = positionLevelOne;
    }

    public PositionLevelOne getPositionLevelOne() {
        return positionLevelOne;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static PositionLevelTwo parse(Integer id) {
        PositionLevelTwo homeType = null; // Default
        for (PositionLevelTwo item : PositionLevelTwo.values()) {
            if (item.getValue().equals(id)) {
                homeType = item;
                break;
            }
        }
        return homeType;
    }

    public static Map asMap() {
        PositionLevelTwo[] values = PositionLevelTwo.values();
        Map<Integer, String> result = new HashMap();
        for (int i = 0; i < values.length; i++) {
            result.put(values[i].getValue(), values[i].getDescription());
        }

        return result;
    }
}