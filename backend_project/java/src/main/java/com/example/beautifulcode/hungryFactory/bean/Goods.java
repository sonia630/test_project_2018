package com.example.beautifulcode.hungryFactory.bean;

import java.math.BigDecimal;

public class Goods {
    private BigDecimal amout;

    public BigDecimal getAmout() {
        amout = BigDecimal.valueOf(200);
        return amout;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }
}
