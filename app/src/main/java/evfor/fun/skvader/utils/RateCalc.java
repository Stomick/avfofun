package evfor.fun.skvader.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import evfor.fun.skvader.R;

public class RateCalc {

    private static Rate[] rateLevels = {
            new Rate(R.color.cabin, R.string.cabin,44),
            new Rate(R.color.master, R.string.master,249),
            new Rate(R.color.cap, R.string.cap,499),
            new Rate(R.color.skipper, R.string.skipper,499)
    };

    static public String getRate(Context context, int level) {
        return context.getString(getCurrentRate(level).stringId);
    }

    static public int getRating(int level) {
        return getCurrentRate(level).maxRate;
    }

    static public Integer getMaxRate() {
        return 250;
    }

    static public int getColor(Context context, int level) {
        return ContextCompat.getColor(context, getCurrentRate(level).colorId);
    }

    static class Rate {
        @ColorRes
        int colorId;
        @StringRes
        int stringId;
        int maxRate;
        Rate(int colorId, int stringId,int rate) {
            this.colorId = colorId;
            this.stringId = stringId;
            maxRate = rate;
        }
    }

    private static Rate getCurrentRate(int level) {
        if (level > 0 && level <= rateLevels.length)
            return rateLevels[level - 1];
        return rateLevels[0];
    }
}
