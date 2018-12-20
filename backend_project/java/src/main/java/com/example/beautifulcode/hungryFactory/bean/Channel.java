package com.example.beautifulcode.hungryFactory.bean;

import java.math.BigDecimal;


public class Channel {
    private BigDecimal disCount;

    public BigDecimal getDisCount() {
        disCount = BigDecimal.valueOf(0.4);
        return disCount;
    }

    public void setDisCount(BigDecimal disCount) {
        this.disCount = disCount;
    }
}
