package com.alexberemart.jhtultimate.model.vo;

import java.util.ArrayList;
import java.util.List;

public class StartupOptions {

    protected List<StartupOptionsPositions> minPositions = new ArrayList<StartupOptionsPositions>();
    protected List<PlayerPosition> fixedPlayerPositions = new ArrayList<>();

    public List<StartupOptionsPositions> getMinPositions() {
        return minPositions;
    }

    public List<PlayerPosition> getFixedPlayerPositions() {
        return fixedPlayerPositions;
    }

    public void setFixedPlayerPositions(List<PlayerPosition> fixedPlayerPositions) {
        this.fixedPlayerPositions = fixedPlayerPositions;
    }

    public void setMinPositions(List<StartupOptionsPositions> minPositions) {
        this.minPositions = minPositions;
    }
}
