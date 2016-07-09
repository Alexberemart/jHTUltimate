package com.alexberemart.jhtultimate.model.vo;

import com.alexberemart.jhtultimate.model.enums.PlayerPosition;

public class StartupPlayerPosition {

    protected String name;
    protected PlayerPosition position;

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}