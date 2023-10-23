package com.kenjo_coding.androiddevtemplate.domain.entities;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenjo_coding.androiddevtemplate.domain.valueobjects.Form;
import com.kenjo_coding.androiddevtemplate.domain.valueobjects.Sprites;
import com.kenjo_coding.androiddevtemplate.domain.valueobjects.Type;

import java.util.List;
import java.util.stream.Collectors;

public class PokemonDetail {
    public List<Form> forms;
    public Integer height;
    public Integer id;
    public String name;
    public Sprites sprites;
    public List<Type> types;
    public Integer weight;

    public String displayTypes(){
        return "types: " + types.stream()
                        .map(type -> type.type.name)
                        .collect(Collectors.toList());
    }

}
