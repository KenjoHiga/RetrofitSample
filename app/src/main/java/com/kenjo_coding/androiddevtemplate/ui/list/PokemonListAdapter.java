package com.kenjo_coding.androiddevtemplate.ui.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.kenjo_coding.androiddevtemplate.databinding.PokemonLinkRowBinding;
import com.kenjo_coding.androiddevtemplate.domain.entities.PokemonLink;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    private List<PokemonLink> links;
    private OnItemClickListener listener; // itemクリック時のリスナー用

    // DataBindingを活用したカスタムViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // item単位でDataBindingする
        private final PokemonLinkRowBinding binding;
        MyViewHolder(PokemonLinkRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    // ViewHolderの生成
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // ViewHolder生成時にDataBindingインスタンスを引数で渡す
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PokemonLinkRowBinding binding = PokemonLinkRowBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(binding);
    }

    // 1行分のデータをViewHolderにセット
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // positionより対象itemオブジェクトをDataBinding
        holder.binding.setLink(links.get(position));
        // itemクリック時のリスナー
        holder.itemView.setOnClickListener(view -> listener.onClick(view, links.get(position)));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPokemonLinks(List<PokemonLink> links) {
        this.links = links;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return links == null ? 0 : links.size();
    }


    /** itemクリック時のリスナーの設定 */
    // fragment側から読込み用
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    // itemClick用にリスナーをカスタム
    public interface  OnItemClickListener {
        // 対象のitemオブジェクトごと渡す
        void onClick(View view, PokemonLink pokemon);
    }


}