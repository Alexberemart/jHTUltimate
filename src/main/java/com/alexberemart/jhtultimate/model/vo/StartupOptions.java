package com.alexberemart.jhtultimate.model.vo;

import java.util.ArrayList;
import java.util.List;

public class StartupOptions {

    protected List<StartupOptionsPositions> minPositions = new ArrayList<StartupOptionsPositions>();
    protected List<StartupPlayerPosition> fixedStartupPlayerPositions = new ArrayList<>();

    public List<StartupOptionsPositions> getMinPositions() {
        return minPositions;
    }

    public List<StartupPlayerPosition> getFixedStartupPlayerPositions() {
        return fixedStartupPlayerPositions;
    }

    public void setFixedStartupPlayerPositions(List<StartupPlayerPosition> fixedStartupPlayerPositions) {
        this.fixedStartupPlayerPositions = fixedStartupPlayerPositions;
    }

    public void setMinPositions(List<StartupOptionsPositions> minPositions) {
        this.minPositions = minPositions;
    }
}
