package com.alexberemart.jhtultimate.model.enums;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum PositionLevelOne {

    K(1, "prueba", 1),
    D(2, "prueba", 5),
    M(3, "prueba", 5),
    A(4, "prueba", 3);

    protected Integer value;
    protected String description;

    public Integer getMaxNumberOfPlayer() {
        return maxNumberOfPlayer;
    }

    protected Integer maxNumberOfPlayer;

    PositionLevelOne(Integer code, String description, Integer maxNumberOfPlayer) {
        this.value = code;
        this.description = description;
        this.maxNumberOfPlayer = maxNumberOfPlayer;
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
    public static PositionLevelOne parse(Integer id) {
        PositionLevelOne homeType = null; // Default
        for (PositionLevelOne item : PositionLevelOne.values()) {
            if (item.getValue().equals(id)) {
                homeType = item;
                break;
            }
        }
        return homeType;
    }

    public static Map asMap() {
        PositionLevelOne[] values = PositionLevelOne.values();
        Map<Integer, String> result = new HashMap();
        for (int i = 0; i < values.length; i++) {
            result.put(values[i].getValue(), values[i].getDescription());
        }

        return result;
    }
}