package com.alexberemart.jhtultimate.model.enums;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum PlayerPosition{

    KEE(1, "prueba", PositionLevelTwo.KE),

    DCN(2, "prueba", PositionLevelTwo.DC),
    DCL(3, "prueba", PositionLevelTwo.DC),
    DCO(4, "prueba", PositionLevelTwo.DC),

    DLN(5, "prueba", PositionLevelTwo.DL),
    DLC(6, "prueba", PositionLevelTwo.DL),
    DLD(7, "prueba", PositionLevelTwo.DL),
    DLO(8, "prueba", PositionLevelTwo.DL),

    MCN(9, "prueba", PositionLevelTwo.MC),
    MCD(10, "prueba", PositionLevelTwo.MC),
    MCO(11, "prueba", PositionLevelTwo.MC),
    MCL(12, "prueba", PositionLevelTwo.MC),

    MLN(13, "prueba", PositionLevelTwo.ML),
    MLD(14, "prueba", PositionLevelTwo.ML),
    MLO(15, "prueba", PositionLevelTwo.ML),
    MLC(16, "prueba", PositionLevelTwo.ML),

    ACN(17, "prueba", PositionLevelTwo.AC),
    ACD(18, "prueba", PositionLevelTwo.AC),
    ACL(19, "prueba", PositionLevelTwo.AC);

    protected Integer value;
    protected String description;
    protected PositionLevelTwo positionLevelTwo;

    PlayerPosition(Integer code, String description, PositionLevelTwo positionLevelTwo) {
        this.value = code;
        this.description = description;
        this.positionLevelTwo = positionLevelTwo;
    }

    public String getDescription() {
        return description;
    }

    public PositionLevelTwo getPositionLevelTwo() {
        return positionLevelTwo;
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

    public String getPositionLevelOneText(){
        return positionLevelTwo.getPositionLevelOne().toString();
    }
}