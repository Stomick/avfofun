package evfor.fun.skvader.ui.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewUtils {
    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.ttf"));
            }
        } catch (Exception e) {
        }
    }

    public static void clearAllTV(View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    clearAllTV(child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setText("");
            }
        } catch (Exception ignored) {
        }
    }

    public static void setTextView(TextView textView, @StringRes int string) {
        if (string == 0)
            textView.setText("");
        else
            try {
                textView.setText(string);
            }catch (Exception e){}
    }

    public static boolean checkEmptyTV(TextView textView, String errorMessage) {
        if (textView.getText().toString().trim().isEmpty()) {
            textView.setError(errorMessage);
            textView.requestFocus();
            return false;
        } else return true;
    }

    public static boolean checkNotEqualsTempTV(TextView textView, String temp, String errorMessage) {
        ClearError clearError = null;
        if (textView.getText().toString().equals(temp)) {

            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
            textView.setClickable(true);
            textView.setError(errorMessage);
            if(clearError==null) {
                clearError = new ClearError(textView);
                textView.addTextChangedListener(clearError);
            }
            return false;
        } else return true;
    }

    private static class ClearError implements TextWatcher {
        private TextView view;

        ClearError(TextView view) {
            this.view = view;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            view.setError(null);
            view.removeTextChangedListener(this);
        }
    }
}
