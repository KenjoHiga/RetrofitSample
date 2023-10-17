package com.kenjo_coding.androiddevtemplate.domain.valueobjects;

import com.google.gson.annotations.SerializedName;

public class Other {
    @SerializedName("dream_world")
    public DreamWorld dreamWorld;

    @SerializedName("official-artwork")
    public OfficialArtwork officialArtwork;
}