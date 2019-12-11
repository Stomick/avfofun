package com.team.noty.event.ui.utils;

import android.content.Context;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.utils.StringUtils;

public class ResUtils {

    public static void setPrice(String price, TextView textView){
        setPrice(StringUtils.toIntOr0(price), textView);
    }

    public static void setPrice(int price, TextView textView){
        if(price<=0)
            textView.setText(textView.getContext().getString(R.string.free));
        else
            textView.setText(textView.getContext().getString(R.string.currency_template, price));
    }

    public static String getCountText(int c, Context context) {
        return context.getString(R.string.already_template, String.valueOf(c));
    }
}
