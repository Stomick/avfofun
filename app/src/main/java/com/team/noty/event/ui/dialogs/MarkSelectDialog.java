package com.team.noty.event.ui.dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.ui.adapters.BaseAdapter;
import com.team.noty.event.ui.utils.RecyclerUtils;
import com.team.noty.event.utils.DPPX;
import com.team.noty.event.utils.callbacks.CallBack1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.team.noty.event.ui.adapters.BaseAdapter.inflate;

public class MarkSelectDialog extends DialogFragment {

    public CallBack1<Integer> setMark;
    Unbinder unbinder;
    Adapter adapter;
    @BindView(R.id.mark_list)
    RecyclerView recyclerView;

    public void setSetMark(CallBack1<Integer> setMark) {
        this.setMark = setMark;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_give_feedback, container, false);
        unbinder = ButterKnife.bind(this, v);
        v.setOnClickListener(view -> dismiss());
        initView();
        return v;
    }

    private void initView() {
        adapter = new Adapter();
        RecyclerUtils.setVerticalAdapter(recyclerView, adapter);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        @Override
        public int getItemViewType(int position) {
            return R.layout.mark_item;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(inflate(parent, viewType));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.mark_text)
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this::onClickListener);
                textView.setOnClickListener(this::onClickListener);
            }

            private void onClickListener(View v) {
                if (setMark != null){
                    setMark.call(getAdapterPosition() + 1);
                    dismiss();
                }
            }

            public void bind(int pos) {
                textView.setText(String.valueOf(pos + 1));
            }
        }
    }
}
