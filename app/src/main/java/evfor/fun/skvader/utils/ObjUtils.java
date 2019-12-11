package evfor.fun.skvader.utils;

import java.util.List;

public class ObjUtils {

    public static <T> boolean equals(T a, T b) {
        return String.valueOf(a).equalsIgnoreCase(String.valueOf(b));
    }

    public static <T> T nullEquals(T original, T edit) {
        if (original == null || !String.valueOf(original).equals(String.valueOf(edit)) && edit != null)
            return edit;
        return null;
    }

    public static int nullEquals(int original, int edit) {
       if(original==edit)
            return original;
       else
            return edit;
    }

    public static <T> T defaultIfNull(T newObj, T defObj) {
        if (newObj == null)
            return defObj;
        else return newObj;
    }

    public static <T> T nullNotEquals(T original, T value) {
        if (original != null && value != null && original.equals(value))
            return original;
        else return null;
    }

    public static <T> T getFirst(List<T> list) {
        if (list.size() > 0)
            return list.get(0);
        else return null;
    }

    public static boolean notNull(Object o) {
        return o != null;
    }
}
