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
import com.kenjo_coding.androiddevtemplate.databinding.FragmentPokemonLinksBinding;
import com.kenjo_coding.androiddevtemplate.ui.PokemonViewModel;
import com.kenjo_coding.androiddevtemplate.ui.detail.PokemonDetailFragment;

import java.util.List;

public class PokemonListFragment extends Fragment {
    private final String TAG = PokemonListFragment.class.getSimpleName();
    private FragmentPokemonLinksBinding binding;
    private PokemonViewModel viewModel;
    private PokemonListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // DataBinding用のインスタンスを生成
        binding = FragmentPokemonLinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModelインスタンスの生成
        viewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        // ポケモンデータを取得
        binding.fetchPokemonLinks.setOnClickListener(v -> viewModel.onFetchPokemonClicked());

        // 次の10匹押下
        binding.next10.setOnClickListener(v -> viewModel.onNext10Clicked());

        // RecyclerViewの初期設定
        initializeRecyclerView();

        // ポケモンデータ取得状況を監視
        viewModel.getPokemonLinks().observe(getViewLifecycleOwner(), links -> {
            if(links == null) return;
            adapter.setPokemonLinks(links);
        });
    }

    // RecyclerViewの初期設定
    private void initializeRecyclerView() {
        adapter = new PokemonListAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // ListViewクリック時の動作（adapter経由でリスナー取得）
        adapter.setOnItemClickListener((v, link) -> {
            // 選択されたpokemon情報をViewModelに引き渡し
            viewModel.onPokemonLinkClicked(link);
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