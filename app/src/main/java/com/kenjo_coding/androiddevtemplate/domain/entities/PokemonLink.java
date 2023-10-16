package com.kenjo_coding.androiddevtemplate.domain.entities;

public class PokemonLink {
    public String name;
    public String url;

    public String id(){
        return url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "");
    }
}
