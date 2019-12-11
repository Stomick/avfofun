package com.team.noty.event.ui.utils.listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SimpleTextListener {

    private TextChangedListener listener;
    private EditText editText;
    private TextWatcher watcher;

    public static SimpleTextListener on(EditText editText, TextChangedListener listener) {
        SimpleTextListener textListener = new SimpleTextListener();
        textListener.listener = listener;
        textListener.editText = editText;
        textListener.setListener();
        return textListener;
    }

    public void remove() {
        if (editText != null)
            editText.removeTextChangedListener(watcher);
        editText = null;
        watcher = null;
    }

    private void setListener() {
        if (editText != null)
            editText.addTextChangedListener(create());
    }

    private TextWatcher create() {
        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText.removeTextChangedListener(this);
                listener.onTextChanged(charSequence.toString().toLowerCase());
                editText.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        return watcher;
    }

    private SimpleTextListener() {
    }

    public interface TextChangedListener {
        void onTextChanged(CharSequence charSequence);
    }
}
