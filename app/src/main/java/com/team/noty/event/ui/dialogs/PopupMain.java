package com.team.noty.event.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.utils.DPPX;
import com.team.noty.event.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PopupMain extends DialogFragment {

    public static final String TAG = PopupMain.class.getSimpleName();

    @BindView(R.id.popup_list)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private List<String> list;
    private CallBack1<Integer> listener;
    private int selected = 0;
    private int pos;
    @BindView(R.id.top)
    View top;

    public PopupMain() {
        list = new ArrayList<>();
    }

    public static PopupMain createPopup(List<String> list) {
        PopupMain popupMain = new PopupMain();
        popupMain.list = list;
        return popupMain;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setListener(CallBack1<Integer> listener) {
        this.listener = listener;
    }

    public void show(FragmentManager fragmentManager, int selected){
        this.selected = selected;
        show(fragmentManager, TAG);
    }

    public static void show(List<String> list, FragmentManager fragmentManager) {
        createPopup(list).show(fragmentManager, TAG);
    }

    public static void showWithDismissListener(List<String> list,
                                               FragmentManager fragmentManager,
                                               CallBack1<Integer> listener,
                                               int selected) {
        PopupMain popupMain = createPopup(list);
        popupMain.listener = listener;
        popupMain.selected = selected;
        popupMain.show(fragmentManager, TAG);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.popup_checked_list, container, false);
        unbinder = ButterKnife.bind(this, v);
        v.setOnClickListener(view -> dismiss());
        initView();
        return v;
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new Adapter());
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
            top.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, pos));
            if(dialog.getWindow()==null)
                return;
            dialog.getWindow().getAttributes().gravity = Gravity.TOP;
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null)
            listener.call(selected);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        private int prewChecked = -1;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.popup_checked_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.popup_item_button)
            RadioButton radioButton;
            @BindView(R.id.popup_item_text)
            TextView textView;
            @BindView(R.id.under_line)
            View under_line;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this::radioOnClick);
            }

            void bind(int position) {
                radioButton.setChecked(selected == position);
                textView.setText(list.get(position));
                if (position == list.size() - 1)
                    under_line.setVisibility(View.GONE);
            }

            void radioOnClick(View v) {
                radioButton.setChecked(true);
                prewChecked = selected;
                selected = getAdapterPosition();
                dismiss();
                notifyItemChanged(prewChecked);
            }
        }
    }
}
