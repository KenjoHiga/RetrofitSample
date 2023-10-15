package com.kenjo_coding.androiddevtemplate.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ConfirmDialog extends DialogFragment {

    public ConfirmDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `create` instead as shown below
    }

    public static class Builder {
        Bundle bundle  = new Bundle();

        public Builder setTitle(String title){
            this.bundle.putString("TitleKey", title);
            return this;
        }

        public Builder setMessage(String message){
            this.bundle.putString("MessageKey", message);
            return this;
        }

        public Builder setPositive(String positive){
            this.bundle.putString("PositiveKey", positive);
            return this;
        }

        public Builder setNegative(String negative){
            this.bundle.putString("NegativeKey", negative);
            return this;
        }

        // ConfirmDialog インスタンス生成＆設定した値を反
        public ConfirmDialog build(){
            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setArguments(this.bundle);
            return dialog;
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Bundleに詰めた値を取り出す
        assert getArguments() != null;
        builder
                // titleとmessage
                .setTitle(getArguments().getString("TitleKey"))
                .setMessage(getArguments().getString("MessageKey"))

                // positiveボタン押下時
                .setPositiveButton(getArguments().getString("PositiveKey"), (dialog, id) -> {
                    // FragmentManager経由で呼び出し元へFragmentResultを渡す
                    getParentFragmentManager().setFragmentResult("PositiveClicked", new Bundle());
                    // Dialogを閉じる
                    dismiss();
                })

                // negativeボタン押下時
                .setNegativeButton(getArguments().getString("NegativeKey"), (dialog, id) -> {
                    // FragmentManager経由で呼び出し元へFragmentResultを渡す
                    getParentFragmentManager().setFragmentResult("NegativeClicked", new Bundle());
                    // Dialogを閉じる
                    dismiss();
                })

        ; // Bundleからの取り出しの終了

        return builder.create();
    }
}