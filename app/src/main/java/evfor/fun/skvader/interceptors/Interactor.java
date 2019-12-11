package evfor.fun.skvader.interceptors;

public interface Interactor<Out, In> {
    Out call(In in);
}
