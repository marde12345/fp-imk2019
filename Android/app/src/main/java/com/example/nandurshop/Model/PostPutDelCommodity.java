package com.example.nandurshop.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelCommodity {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Commodity mKontak;
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
    public Commodity getKontak() {
        return mKontak;
    }
    public void setKontak(Commodity Kontak) {
        mKontak = Kontak;
    }

}
