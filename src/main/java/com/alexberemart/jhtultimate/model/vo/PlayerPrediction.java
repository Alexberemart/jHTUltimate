package com.alexberemart.jhtultimate.model.vo;

import com.alexberemart.jhtultimate.model.enums.PlayerPosition;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PlayerPrediction {

    protected String name;
    protected PlayerPosition attribute;
    protected Double value;

    public String getName() {
        return name;
    }

    public PlayerPosition getAttribute() {
        return attribute;
    }

    public String getAttributeDescription() {
        return attribute.getPositionLevelOneText();
    }

    public void setAttribute(PlayerPosition attribute) {
        this.attribute = attribute;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
