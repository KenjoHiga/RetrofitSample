package com.kenjo_coding.androiddevtemplate.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenjo_coding.androiddevtemplate.domain.entities.PokemonDetail;
import com.kenjo_coding.androiddevtemplate.domain.entities.PokemonLink;
import com.kenjo_coding.androiddevtemplate.domain.entities.PokemonsResponse;
import com.kenjo_coding.androiddevtemplate.infrastructure.ApiService;
import com.kenjo_coding.androiddevtemplate.infrastructure.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PokemonViewModel extends ViewModel {
    private static final String TAG = PokemonViewModel.class.getSimpleName();

    private final int SEARCH_LIMIT = 10; // 一度に取得するポケモンの数
    private int searchOffset = 0; // 先頭IDからのオフセット

    /** ポケモンリストの処理 */
    // 定義
    private MutableLiveData<List<PokemonLink>> _pokemonLinks = new MutableLiveData<>();

    // 参照
    public LiveData<List<PokemonLink>> getPokemonLinks() {
        return _pokemonLinks;
    }

    // 取得
    public void onFetchPokemonClicked(){
        searchOffset = 0;
        fetchPokemon();
    }

    public void onNext10Clicked(){
        searchOffset += 10;
        fetchPokemon();
    }

    private void fetchPokemon() {

        RetrofitClient client = new RetrofitClient();
        ApiService api = client.createApiService();

        CompositeDisposable disposable = new CompositeDisposable();

        disposable.add(api.getPokemonLinks(String.valueOf(SEARCH_LIMIT), String.valueOf(searchOffset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PokemonsResponse>() {
                    @Override
                    public void onSuccess(@NonNull PokemonsResponse response) {
                        _pokemonLinks.setValue(response.results);
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "e.getMessage():" + e.getMessage());
                    }
                })
        );
    }


    /** 選択したポケモンの処理 */
    // 定義
    private  MutableLiveData<PokemonDetail> _targetPokemonDetail = new MutableLiveData<>();

    // 参照
    public LiveData<PokemonDetail> getTargetPokemonDetail(){
        return _targetPokemonDetail;
    }

    // セット
    public void onPokemonLinkClicked(PokemonLink link){
        RetrofitClient client = new RetrofitClient();
        ApiService api = client.createApiService();

        CompositeDisposable disposable = new CompositeDisposable();

        disposable.add(api.getPokemon(link.id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PokemonDetail>() {
                    @Override
                    public void onSuccess(@NonNull PokemonDetail response) {

                         Gson gson = new GsonBuilder().setPrettyPrinting().create();
                         Log.d(TAG, "gson.toJson(response):" + gson.toJson(response));

                        _targetPokemonDetail.setValue(response);
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "e.getMessage():" + e.getMessage());
                    }
                })
        );
    }

}