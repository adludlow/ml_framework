package com.ls.ai.ml;

/**
 * Created by aludlow on 28/11/2015.
 */
public class Weight {
    private Double value;
    private Boolean isZeroWeight = false;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isZeroWeight(){
        return isZeroWeight;
    }

    public void setIsZeroWeight(Boolean zeroWeight) {
        isZeroWeight = zeroWeight;
    }
}
