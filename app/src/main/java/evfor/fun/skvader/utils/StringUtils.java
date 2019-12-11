package evfor.fun.skvader.utils;

public class StringUtils {
    public static boolean containsNumber(String string) {
        return !string.replaceAll("[\\D]", "").isEmpty();
    }

    public static String onlyNumber(String str) {
        return str.replaceAll("[\\D]", "");
    }

    public static boolean validEmail(String email) {
        return (!email.isEmpty()) && email.contains("@") && email.contains(".");
    }

    public static int toInt(String str) {
        return Integer.parseInt(onlyNumber(str));
    }

    public static String toStringEmptyIf0(int n) {
        return n == 0 ? "" : String.valueOf(n);
    }

    public static String toStringEmptyIf0(Integer n) {
        return n == null || n == 0 ? "" : String.valueOf(n);
    }

    public static int toIntOr0(String str) {
        if (str == null)
            return 0;
        if (onlyNumber(str).isEmpty())
            return 0;
        else return toInt(str);
    }

    public static long toLongOr0(String str) {
        if (onlyNumber(str).isEmpty())
            return 0;
        else return toInt(str);
    }

    public static String emptyIfNull(Object o) {
        return o == null ? "" : String.valueOf(o);
    }
}
