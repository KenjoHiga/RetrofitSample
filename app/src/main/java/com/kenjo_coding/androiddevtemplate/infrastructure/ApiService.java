package com.kenjo_coding.androiddevtemplate.infrastructure;

import com.kenjo_coding.androiddevtemplate.domain.entities.PokemonDetail;
import com.kenjo_coding.androiddevtemplate.domain.entities.PokemonsResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("pokemon")
    Single<PokemonsResponse> getPokemonLinks(
            @Query("limit") String limit, @Query("offset") String offset);

    @GET("pokemon/{id}")
    Single<PokemonDetail> getPokemon(
            @Path("id") String id);
}
