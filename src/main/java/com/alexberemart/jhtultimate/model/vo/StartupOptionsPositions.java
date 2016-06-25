package com.alexberemart.jhtultimate.model.vo;

import com.alexberemart.jhtultimate.model.enums.PlayerPosition;

public class StartupOptionsPositions {

    protected PlayerPosition position;
    protected Integer value;

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
