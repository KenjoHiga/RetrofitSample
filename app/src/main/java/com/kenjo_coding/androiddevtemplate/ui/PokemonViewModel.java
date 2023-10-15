package com.kenjo_coding.androiddevtemplate.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.kenjo_coding.androiddevtemplate.model.Pokemon;
import com.kenjo_coding.androiddevtemplate.model.PokemonList;

import java.util.List;


public class PokemonViewModel extends ViewModel {
    private static final String TAG = PokemonViewModel.class.getSimpleName();

    /** ポケモンリストの処理 */
    // 定義
    private MutableLiveData<List<Pokemon>> _pokemons = new MutableLiveData<>();

    // セット
    public void onFetchPokemonsClicked() {
        _pokemons.setValue(generatePokemons());
    }

    // 参照
    public LiveData<List<Pokemon>> getPokemons() {
        return _pokemons;
    }




    /** 選択したポケモンの処理 */

    // 定義
    private Pokemon targetPokemon;

    // セット
    public void setPokemon(Pokemon pokemon){
        this.targetPokemon = pokemon;
    }

    // 参照
    public Pokemon getTargetPokemon(){
        return targetPokemon;
    }

    private List<Pokemon> generatePokemons(){
        String pokemonJson = getPokemonJson();

        Gson gson = new Gson();
        //Gsonでパース
        List<Pokemon> pokemons = new Gson().fromJson(pokemonJson, PokemonList.class).getPokemons();

        return pokemons;
    }

    private String getPokemonJson() {
        return "{\n" +
                "  \"pokemons\": [\n" +
                "    {\n" +
                "      \"height\": 7,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"フシギダネ\",\n" +
                "      \"type\": \"くさ\",\n" +
                "      \"weight\": 69\n" +
                "    },\n" +
                "    {\n" +
                "      \"height\": 10,\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"フシギソウ\",\n" +
                "      \"type\": \"くさ\",\n" +
                "      \"weight\": 130\n" +
                "    },\n" +
                "    {\n" +
                "      \"height\": 20,\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"フシギバナ\",\n" +
                "      \"type\": \"くさ\",\n" +
                "      \"weight\": 1000\n" +
                "    },\n" +
                "    {\n" +
                "      \"height\": 6,\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"ヒトカゲ\",\n" +
                "      \"type\": \"ほのお\",\n" +
                "      \"weight\": 85\n" +
                "    },\n" +
                "    {\n" +
                "      \"height\": 11,\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"リザード\",\n" +
                "      \"type\": \"ほのお\",\n" +
                "      \"weight\": 190\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}