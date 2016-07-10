package com.alexberemart.jhtultimate.model.vo;

import com.alexberemart.jhtultimate.model.enums.PlayerPosition;

public class StartupEntry {

    protected String name;
    protected PlayerPosition position;
    protected Double value;
    protected Integer maxRange;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getAttributeDescription() {
        return position.getPositionLevelOneText();
    }
}
