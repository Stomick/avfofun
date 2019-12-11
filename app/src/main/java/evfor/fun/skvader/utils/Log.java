package evfor.fun.skvader.utils;

public class Log {

    public static void d(Class clas, String log){
        android.util.Log.i(clas.getSimpleName(), log);
    }

    public static void d(Class clas, String log, Throwable t){
        android.util.Log.i(clas.getSimpleName(), log, t);
    }

    public static void wot(String message){
        android.util.Log.i("TAG", message);
    }
    public static void wot(String message, Throwable t){
        android.util.Log.i("TAG", message, t);
    }
}
