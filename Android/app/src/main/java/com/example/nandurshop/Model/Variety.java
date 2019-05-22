package com.example.nandurshop.Model;

import com.google.gson.annotations.SerializedName;

public class Variety {
    @SerializedName("name")
    private String name;
    @SerializedName("duration")
    private Integer duration;

    public Variety(String name, Integer duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
