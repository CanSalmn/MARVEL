package com.cansalman.marvelbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComicsModel {


    @SerializedName("available")
    @Expose
    public  int comicsSize;
}
