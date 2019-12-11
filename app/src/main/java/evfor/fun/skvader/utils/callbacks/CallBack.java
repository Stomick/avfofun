package evfor.fun.skvader.utils.callbacks;

public interface CallBack {
    void call();

    CallBack empty = () -> {};
}
