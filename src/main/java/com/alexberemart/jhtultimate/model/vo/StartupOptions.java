package com.alexberemart.jhtultimate.model.vo;

import java.util.ArrayList;
import java.util.List;

public class StartupOptions {

    protected List<StartupOptionsPositions> minPositions = new ArrayList<StartupOptionsPositions>();
    protected List<StartupPlayerPosition> fixedStartupPlayerPositions = new ArrayList<>();
    protected Integer maxRange;

    public List<StartupOptionsPositions> getMinPositions() {
        return minPositions;
    }

    public List<StartupPlayerPosition> getFixedStartupPlayerPositions() {
        return fixedStartupPlayerPositions;
    }

    public void setFixedStartupPlayerPositions(List<StartupPlayerPosition> fixedStartupPlayerPositions) {
        this.fixedStartupPlayerPositions = fixedStartupPlayerPositions;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    public void setMinPositions(List<StartupOptionsPositions> minPositions) {
        this.minPositions = minPositions;
    }
}
