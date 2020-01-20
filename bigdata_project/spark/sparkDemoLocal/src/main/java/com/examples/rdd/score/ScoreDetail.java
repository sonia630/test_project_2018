package com.examples.rdd.score;

import java.io.Serializable;

public class ScoreDetail implements Serializable {
    public String name;
    public String subject;
    public Float score;

    public ScoreDetail(String name, String subject, Float score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreDetail{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                '}';
    }
}
