package com.example.nandurshop.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Commodity implements Serializable {
    @SerializedName("variety_id")
    private Integer variety_id;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("planted_at")
    private String planted_at;

    public Commodity(String name, String image_url, String planted_at, Integer variety_id) {
        this.id = id;
        this.name = name;
        this.variety_id = variety_id;
        this.image_url = image_url;
        this.planted_at = planted_at;
    }

    public Commodity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public Integer getVarietyId() {
        return variety_id;
    }

    public void setVarietyId(Integer variety_id) {
        this.variety_id = variety_id;
    }

    public String getPlantedAt() {
        return planted_at;
    }

    public void setPlantedAt(String planted_at) {
        this.planted_at = planted_at;
    }
}
