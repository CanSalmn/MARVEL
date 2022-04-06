package com.cansalman.marvelbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroesModel {

    @SerializedName("id")
    @Expose
    public  double id;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("name")
    @Expose
    public  String name;


    @SerializedName("thumbnail")
    @Expose
    public CharacterThumNail characterThumNail;



    @SerializedName("comics")
    @Expose
    public ComicsModel comicsModel;



}
