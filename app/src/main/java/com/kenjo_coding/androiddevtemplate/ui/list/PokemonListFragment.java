package com.kenjo_coding.androiddevtemplate.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kenjo_coding.androiddevtemplate.R;
import com.kenjo_coding.androiddevtemplate.databinding.FragmentPokemonListBinding;
import com.kenjo_coding.androiddevtemplate.model.Pokemon;
import com.kenjo_coding.androiddevtemplate.ui.PokemonViewModel;
import com.kenjo_coding.androiddevtemplate.ui.detail.PokemonDetailFragment;

import java.util.List;

public class PokemonListFragment extends Fragment {
    private final String TAG = PokemonListFragment.class.getSimpleName();
    private FragmentPokemonListBinding binding;
    private PokemonViewModel viewModel;
    private PokemonListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // DataBinding用のインスタンスを生成
        binding = FragmentPokemonListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModelインスタンスの生成
        viewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        // ポケモンデータを取得
        binding.fetchPokemonData.setOnClickListener(v -> viewModel.onFetchPokemonsClicked());

        // ポケモンデータ取得状況を監視
        viewModel.getPokemons().observe(getViewLifecycleOwner(), pokemons -> {
            if(pokemons == null || pokemons.size() ==0) return;
            setRecyclerView(pokemons);
        });
    }


    // recyclerViewにポケモンリストをセット
    private void setRecyclerView(List<Pokemon> pokemons) {
        adapter = new PokemonListAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // ReceivingItemsをセット
        adapter.setPokemons(pokemons);

        // ListViewクリック時の動作（adapter経由でリスナー取得）
        adapter.setOnItemClickListener((v, pokemon) -> {
            // 選択されたpokemon情報をViewModelに引き渡し
            viewModel.setPokemon(pokemon);
            navigate(new PokemonDetailFragment());
        });
    }


    private void navigate(Fragment fragment) {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
    }

}