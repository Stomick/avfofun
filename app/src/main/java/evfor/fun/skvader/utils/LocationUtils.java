package evfor.fun.skvader.utils;

import java.util.Locale;

public class LocationUtils {
    public static String getLocCode(){
        String loc = Locale.getDefault().getISO3Language();
        if (loc.length() > 2)
            loc = loc.substring(0, 2);
        return "ru";
    }
}
