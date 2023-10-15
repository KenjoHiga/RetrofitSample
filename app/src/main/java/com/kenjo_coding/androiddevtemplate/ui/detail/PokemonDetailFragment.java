package com.kenjo_coding.androiddevtemplate.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kenjo_coding.androiddevtemplate.R;
import com.kenjo_coding.androiddevtemplate.databinding.FragmentPokemonDetailBinding;
import com.kenjo_coding.androiddevtemplate.ui.PokemonViewModel;
import com.kenjo_coding.androiddevtemplate.ui.list.PokemonListFragment;

public class PokemonDetailFragment extends Fragment {
    private FragmentPokemonDetailBinding binding;
    private PokemonViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // DataBinding用のインスタンスを生成
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModelインスタンスの生成
        viewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        // 前画面で選択済のポケモン情報をセット（DataBinding）
        binding.setPokemon(viewModel.getTargetPokemon());

        // 閉じるボタン押下（リストに戻る）
        binding.close.setOnClickListener(v -> navigate(new PokemonListFragment()));
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