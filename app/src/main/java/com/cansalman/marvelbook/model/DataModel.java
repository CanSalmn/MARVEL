package com.cansalman.marvelbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {


    @SerializedName("data")
    @Expose
    public  CharacterModel characterModel;


}
