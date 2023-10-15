package com.kenjo_coding.androiddevtemplate.domain.entities;

import java.util.List;

public class PokemonsResponse {
    public Integer count;
    public String next;
    public Object previous;
    public List<PokemonLink> results;
}
