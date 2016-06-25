package com.alexberemart.jhtultimate.model.enums;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum PlayerPosition{

    KEE(1, "Piso / Apartamento"),

    DCN(2, "Piso / Apartamento"),
    DCL(3, "Piso / Apartamento"),
    DCO(4, "Piso / Apartamento"),

    DLN(5, "Piso / Apartamento"),
    DLC(6, "Piso / Apartamento"),
    DLD(7, "Piso / Apartamento"),
    DLO(8, "Piso / Apartamento"),

    MCN(9, "Piso / Apartamento"),
    MCD(10, "Piso / Apartamento"),
    MCO(11, "Piso / Apartamento"),
    MCL(12, "Piso / Apartamento"),

    MLN(13, "Piso / Apartamento"),
    MLD(14, "Piso / Apartamento"),
    MLO(15, "Piso / Apartamento"),
    MLC(16, "Piso / Apartamento"),

    ACN(17, "Piso / Apartamento"),
    ACD(18, "Piso / Apartamento"),
    ACL(19, "Piso / Apartamento");

    protected Integer value;
    protected String description;

    PlayerPosition(Integer code, String description) {
        this.value = code;
        this.description = description;
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
    public static PlayerPosition parse(Integer id) {
        PlayerPosition homeType = null; // Default
        for (PlayerPosition item : PlayerPosition.values()) {
            if (item.getValue().equals(id)) {
                homeType = item;
                break;
            }
        }
        return homeType;
    }

    public static Map asMap() {
        PlayerPosition[] values = PlayerPosition.values();
        Map<Integer, String> result = new HashMap();
        for (int i = 0; i < values.length; i++) {
            result.put(values[i].getValue(), values[i].getDescription());
        }

        return result;
    }
}