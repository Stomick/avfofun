package com.team.noty.event.ui.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.utils.callbacks.CallBack1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EditTextDialog extends DialogFragment {

    @BindView(R.id.textView)
    TextView titleView;
    private CallBack1<String> callBack;
    @BindView(R.id.editText)
    EditText editText;
    private String start, title;
    private Unbinder unbinder;

    public static EditTextDialog create(String title, CallBack1<String> callBack, String start) {
        EditTextDialog dialog = new EditTextDialog();
        dialog.callBack = callBack;
        dialog.start = getSocId(start);
        dialog.title = title;
        return dialog;
    }

    public static String getSocId(String url) {
        if (url.contains("="))
            return url.substring(url.lastIndexOf('=') + 1);
        return url.substring(url.lastIndexOf('/') + 1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_text, container, false);
        unbinder = ButterKnife.bind(this, view);
        titleView.setText(title);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        editText.setText(start);
        Dialog d = getDialog();
        if (d != null && d.getWindow() != null)
            d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @OnClick(R.id.button)
    void onClick(Button button) {
        String url = editText.getText().toString().equals("")?" ":editText.getText().toString();
        editText.setText("");

            int start = url.indexOf('/');
            if (start < 0) start = 0;
            callBack.call(url.substring(start));

        dismiss();
        button.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }
}
