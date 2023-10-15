package com.kenjo_coding.androiddevtemplate.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kenjo_coding.androiddevtemplate.R;
import com.kenjo_coding.androiddevtemplate.databinding.FragmentMenuBinding;
import com.kenjo_coding.androiddevtemplate.ui.list.PokemonListFragment;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // DataBinding用のインスタンスを生成
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ボタン押下時にFragmentをに遷移
        binding.navigatePokemonList.setOnClickListener(v -> navigate(new PokemonListFragment()));
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