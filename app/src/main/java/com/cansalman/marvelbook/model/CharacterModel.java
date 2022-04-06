package com.cansalman.marvelbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterModel {

    @SerializedName("results")
    @Expose
    public List<HeroesModel> heroesModel;

}
