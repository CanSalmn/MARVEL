package com.cansalman.marvelbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterThumNail {

    @SerializedName("path")
    @Expose
    public  String path;


    @SerializedName("extension")
    @Expose
    public  String extension;


}
