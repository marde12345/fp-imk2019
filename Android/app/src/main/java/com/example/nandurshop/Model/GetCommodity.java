package com.example.nandurshop.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCommodity {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Commodity> listDataCommodity;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Commodity> getListDataCommodity() {
        return listDataCommodity;
    }
    public void setListDataCommodity(List<Commodity> listDataCommodity) {
        this.listDataCommodity = listDataCommodity;
    }
}
