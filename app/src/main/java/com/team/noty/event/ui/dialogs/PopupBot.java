package com.team.noty.event.ui.dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.team.noty.event.R;
import com.team.noty.event.ui.adapters.BaseMenuAdapter;
import com.team.noty.event.ui.utils.DimenFromAttr;
import com.team.noty.event.ui.utils.RecyclerUtils;
import com.team.noty.event.utils.DPPX;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PopupBot extends DialogFragment {

    public static final String TAG = PopupMain.class.getSimpleName();
    @BindView(R.id.top)
    View top;
    @BindView(R.id.bot)
    View bot;

    public enum Gravity {
        BOT, TOP
    }

    @BindView(R.id.popup_list)
    RecyclerView recyclerView;
    BaseMenuAdapter adapter;
    private Gravity gravity = Gravity.TOP;
    Unbinder unbinder;

    public PopupBot() {
    }

    public static <T extends BaseMenuAdapter> PopupBot createPopup(T adapter) {
        PopupBot popupBot = new PopupBot();
        popupBot.adapter = adapter;
        return popupBot;
    }

    public static <T extends BaseMenuAdapter> void show(T adapter, FragmentManager fragmentManager) {
        createPopup(adapter).show(fragmentManager, TAG);
    }

    public static <T extends BaseMenuAdapter> void show(T adapter, FragmentManager fragmentManager, Gravity gravity) {
        PopupBot popupBot = createPopup(adapter);
        popupBot.gravity = gravity;
        popupBot.show(fragmentManager, TAG);
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
        RecyclerUtils.setVerticalAdapter(recyclerView, adapter);
        recyclerView.setPadding(0,
                DPPX.toPx(8, getContext().getResources().getDisplayMetrics()),
                0,
                DPPX.toPx(8, getContext().getResources().getDisplayMetrics())
        );
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
            int topMargin = DimenFromAttr.getDimen(android.R.attr.actionBarSize, getContext());
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, topMargin, displayMetrics);
            if (gravity == Gravity.TOP)
                dialog.getWindow().getAttributes().height = displayMetrics.heightPixels - topMargin;
            if (gravity == Gravity.BOT) {
                top.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.popup_bg));
                top.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
                bot.setVisibility(View.GONE);
            }
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
