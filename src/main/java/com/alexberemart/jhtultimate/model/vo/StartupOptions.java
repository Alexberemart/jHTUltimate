package com.alexberemart.jhtultimate.model.vo;

import java.util.ArrayList;
import java.util.List;

public class StartupOptions {

    protected List<StartupOptionsPositions> minPositions = new ArrayList<StartupOptionsPositions>();

    public List<StartupOptionsPositions> getMinPositions() {
        return minPositions;
    }

    public void setMinPositions(List<StartupOptionsPositions> minPositions) {
        this.minPositions = minPositions;
    }
}
