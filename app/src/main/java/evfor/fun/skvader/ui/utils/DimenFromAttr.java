package evfor.fun.skvader.ui.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.util.TypedValue;

public class DimenFromAttr {

    public static int getDimen(@AttrRes int attr, Context context){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textAppearanceLarge, typedValue, true);
        int[] textSizeAttr = new int[] { attr };
        TypedArray a = context.obtainStyledAttributes(typedValue.data, textSizeAttr);
        //a.recycle();
        return a.getDimensionPixelSize(0, -1);
    }
}
