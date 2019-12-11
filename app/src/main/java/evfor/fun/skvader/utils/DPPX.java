package evfor.fun.skvader.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DPPX {
    public static float toDp(int px, DisplayMetrics metrics) {
        return ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int toPx(float dp, DisplayMetrics metrics) {
        return (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int statusBarH(Resources resources){
        int statusBarHeight = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
