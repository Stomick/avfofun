package evfor.fun.skvader.ui.utils.reg_step_view;

import android.view.View;
import android.widget.EditText;

import evfor.fun.skvader.R;
import evfor.fun.skvader.ui.utils.listeners.SimpleTextListener;

import butterknife.BindView;

public class Step3 extends BaseStep {

    @BindView(R.id.editText)
    EditText editText;
    private String text;
    private static String text_to_upload;
    @Override
    public void setView(View view) {
        super.setView(view);
        if (text != null && !text.isEmpty())
            editText.setText(text);
        SimpleTextListener.on(editText, this::setText);
    }

    public void setText(CharSequence text) {
        this.text = text.toString();
    }


    public String getText() {
        return editText.getText().toString();
    }
}
