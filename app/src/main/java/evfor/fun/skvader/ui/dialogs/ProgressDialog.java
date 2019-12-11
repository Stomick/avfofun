package evfor.fun.skvader.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import evfor.fun.skvader.R;
import evfor.fun.skvader.ui.activities.BaseActivity;


public class ProgressDialog extends Dialog {

    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void show() {
        if (!isShowing())
            super.show();
    }

    public void close() {
        if (isShowing())
            super.dismiss();
    }

    @Override
    public void dismiss() {

    }

    @Override
    public void onBackPressed() {
        close();
        if (getContext() instanceof BaseActivity)
            ((Activity) getContext()).onBackPressed();
        else super.onBackPressed();
    }
}
