package evfor.fun.skvader.utils.callbacks;

public interface CallBack1<T> {
    void call(T t);

    static <S> CallBack1<S> empty(){
        return s -> {};
    }
}
