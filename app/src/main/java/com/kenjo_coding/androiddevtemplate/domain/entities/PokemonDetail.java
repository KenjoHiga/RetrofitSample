package com.kenjo_coding.androiddevtemplate.domain.entities;

import com.kenjo_coding.androiddevtemplate.domain.valueobjects.Form;
import com.kenjo_coding.androiddevtemplate.domain.valueobjects.Sprites;
import com.kenjo_coding.androiddevtemplate.domain.valueobjects.Type;

import java.util.List;

public class PokemonDetail {
    public List<Form> forms;
    public Integer height;
    public Integer id;
    public String name;
    public Sprites sprites;
    public List<Type> types;
    public Integer weight;

}
